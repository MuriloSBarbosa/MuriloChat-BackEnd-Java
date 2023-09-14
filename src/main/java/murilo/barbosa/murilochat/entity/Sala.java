package murilo.barbosa.murilochat.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String identificador;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany
    private List<Mensagem> mensagens;
    @OneToMany
    private List<Chat> chats;

    public Sala() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
