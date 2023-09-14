package murilo.barbosa.murilochat.controller;

import jakarta.validation.constraints.NotBlank;
import murilo.barbosa.murilochat.entity.Usuario;
import murilo.barbosa.murilochat.service.usuario.UsuarioService;
import murilo.barbosa.murilochat.service.usuario.dto.UsuarioCriacaoDto;
import murilo.barbosa.murilochat.service.usuario.dto.UsuarioDetalhesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDetalhesDto>> listar() {
        List<UsuarioDetalhesDto> usuarios = usuarioService.listar();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Object> cadastrar(
            @RequestParam("nome") @NotBlank String nome,
            @RequestParam("senha") @NotBlank String senha,
            @RequestParam("perfilImage") MultipartFile multipartFile
    ) {
        return ResponseEntity.status(201).body(usuarioService.cadastrar(nome, senha, multipartFile));
    }

    @GetMapping("/verificar/{nome}")
    public ResponseEntity<UsuarioDetalhesDto> verificarNome(@PathVariable String nome) {
        Usuario usuario = usuarioService.verificarNome(nome);
        return ResponseEntity.status(200).body(new UsuarioDetalhesDto(usuario));
    }
}
