package murilo.barbosa.murilochat.service.usuario;

import murilo.barbosa.murilochat.entity.Usuario;
import murilo.barbosa.murilochat.repository.UsuarioRepository;
import murilo.barbosa.murilochat.service.file.FileService;
import murilo.barbosa.murilochat.service.usuario.dto.UsuarioDetalhesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDetalhesDto> listar() {
        return usuarioRepository.findAll().stream().map(UsuarioDetalhesDto::new).toList();
    }

    public UsuarioDetalhesDto cadastrar(String nome, String senha, MultipartFile multipartFile) {
        String imagePath = null;

        if (!multipartFile.isEmpty()) {
            imagePath = FileService.salvarImagem(multipartFile, "perfil");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setPerfilSrc(imagePath);

        usuarioRepository.save(usuario);

        return new UsuarioDetalhesDto(usuario);
    }

    public Usuario verificarNome(String nome) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(nome);
        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(204));
        }
        return usuarioOpt.get();
    }
}
