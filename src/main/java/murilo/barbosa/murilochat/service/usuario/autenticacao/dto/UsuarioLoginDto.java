package murilo.barbosa.murilochat.service.usuario.autenticacao.dto;

import lombok.Data;

public record UsuarioLoginDto(
        String login,
        String senha
) { }