package murilo.barbosa.murilochat.controller;

import jakarta.validation.constraints.NotBlank;
import murilo.barbosa.murilochat.configuration.security.jwt.GerenciadorTokenJwt;
import murilo.barbosa.murilochat.entity.Usuario;
import murilo.barbosa.murilochat.service.usuario.UsuarioService;
import murilo.barbosa.murilochat.service.usuario.autenticacao.dto.UsuarioLoginDto;
import murilo.barbosa.murilochat.service.usuario.autenticacao.dto.UsuarioTokenDto;
import murilo.barbosa.murilochat.service.usuario.dto.UsuarioInformacoesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioInformacoesDto>> listar() {
        System.out.println("A PARTIR DAQUI É LISTAAAR");
        List<UsuarioInformacoesDto> usuarios = usuarioService.listar();
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

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }


    @GetMapping("/verificar/{nome}")
    public ResponseEntity<UsuarioInformacoesDto> verificarNome(@PathVariable String nome) {
        Usuario usuario = usuarioService.verificarNome(nome);
        return ResponseEntity.status(200).body(new UsuarioInformacoesDto(usuario));
    }


}
