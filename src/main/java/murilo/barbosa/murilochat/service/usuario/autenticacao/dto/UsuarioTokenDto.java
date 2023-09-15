package murilo.barbosa.murilochat.service.usuario.autenticacao.dto;

import murilo.barbosa.murilochat.entity.Usuario;

public record UsuarioTokenDto (
        Integer id,
        String nome,
        String token
){
    public UsuarioTokenDto(Usuario usuario, String token){
        this(usuario.getId(), usuario.getNome(), token);
    }
}
