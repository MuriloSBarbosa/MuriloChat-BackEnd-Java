package murilo.barbosa.murilochat.service.usuario.dto;

import murilo.barbosa.murilochat.entity.Usuario;

public record UsuarioDetalhesDto (
     Integer id,
     String nome,
     String perfilSrc,
     String wallpaperSrc,
     String wallpaperLuminosidade
){
    public UsuarioDetalhesDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getPerfilSrc(), usuario.getWallpaperSrc(), usuario.getWallpaperLuminosidade());
    }
}
