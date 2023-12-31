package murilo.barbosa.murilochat.service.usuario.autenticacao;

import murilo.barbosa.murilochat.entity.Usuario;
import murilo.barbosa.murilochat.repository.UsuarioRepository;
import murilo.barbosa.murilochat.service.usuario.autenticacao.dto.UsuarioDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(username);

        if(usuarioOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("usuario: %s não encontrado!", username));
        }

        return new UsuarioDetailsDto(usuarioOpt.get());
    }
}
