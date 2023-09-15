package murilo.barbosa.murilochat.service.usuario.dto;

import murilo.barbosa.murilochat.entity.Usuario;
import murilo.barbosa.murilochat.service.usuario.autenticacao.dto.UsuarioTokenDto;

public class UsuarioMapper {
    public static UsuarioTokenDto of(Usuario usuario, String token) {
        return new UsuarioTokenDto(usuario, token);
    }
}
