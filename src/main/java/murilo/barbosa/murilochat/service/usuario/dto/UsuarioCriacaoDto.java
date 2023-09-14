package murilo.barbosa.murilochat.service.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record UsuarioCriacaoDto(
        @NotBlank
        String nome,
        @NotBlank
        String senha,
        MultipartFile perfilImage
){

}
