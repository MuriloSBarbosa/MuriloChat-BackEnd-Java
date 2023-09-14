package murilo.barbosa.murilochat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    private LocalDateTime dtMensagem;
    private String srcImage;
    private String srcDoc;
    private String typeDoc;
    private String sizeDoc;
    private boolean isAddUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Sala sala;

    public Mensagem() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
