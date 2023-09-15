package murilo.barbosa.murilochat.service.usuario;

import murilo.barbosa.murilochat.configuration.security.jwt.GerenciadorTokenJwt;
import murilo.barbosa.murilochat.entity.Usuario;
import murilo.barbosa.murilochat.repository.UsuarioRepository;
import murilo.barbosa.murilochat.service.file.FileService;
import murilo.barbosa.murilochat.service.usuario.autenticacao.dto.UsuarioLoginDto;
import murilo.barbosa.murilochat.service.usuario.autenticacao.dto.UsuarioTokenDto;
import murilo.barbosa.murilochat.service.usuario.dto.UsuarioInformacoesDto;
import murilo.barbosa.murilochat.service.usuario.dto.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;


    public List<UsuarioInformacoesDto> listar() {
        return usuarioRepository.findAll().stream().map(UsuarioInformacoesDto::new).toList();
    }

    public UsuarioInformacoesDto cadastrar(String nome, String senha, MultipartFile multipartFile) {
        String imagePath = null;

        if (!multipartFile.isEmpty()) {
            imagePath = FileService.salvarImagem(multipartFile, "perfil");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setPerfilSrc(imagePath);

        usuarioRepository.save(usuario);

        return new UsuarioInformacoesDto(usuario);
    }

    public Usuario verificarNome(String nome) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(nome);
        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(204));
        }
        return usuarioOpt.get();
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.login(), usuarioLoginDto.senha()
        );
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByNome(usuarioLoginDto.login())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatusCode.valueOf(404), "Nome do usuário não encontrado")
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado,token);
    }
}
