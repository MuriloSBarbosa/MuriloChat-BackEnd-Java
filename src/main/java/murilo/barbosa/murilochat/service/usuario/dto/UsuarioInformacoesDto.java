package murilo.barbosa.murilochat.service.usuario.dto;

import murilo.barbosa.murilochat.entity.Usuario;

public record UsuarioInformacoesDto(
     Integer id,
     String nome,
     String perfilSrc,
     String wallpaperSrc,
     String wallpaperLuminosidade
){
    public UsuarioInformacoesDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getPerfilSrc(), usuario.getWallpaperSrc(), usuario.getWallpaperLuminosidade());
    }
}
