package murilo.barbosa.murilochat.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileService {
    public static String salvarImagem(MultipartFile perfilImage, String pathFileType) {

        String projectRoot = new File("").getAbsolutePath();
        String uploadDir = "src/main/java/murilo/barbosa/murilochat/uploads";

        String perfilImageName = LocalDateTime.now().toString().replace(":","-") + perfilImage.getOriginalFilename();

        String uploadPath = String.format("%s/%s/%s/%s", projectRoot, uploadDir, pathFileType, perfilImageName);

        try {
            perfilImage.transferTo(new File(uploadPath));
            return uploadPath;
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed!", e);
        }
    }
}
