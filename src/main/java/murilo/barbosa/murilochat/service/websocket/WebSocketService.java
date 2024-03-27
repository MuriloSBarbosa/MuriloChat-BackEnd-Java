package murilo.barbosa.murilochat.service.websocket;

import lombok.RequiredArgsConstructor;
import murilo.barbosa.murilochat.service.websocket.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor()
public class WebSocketService {
    //
    private final SimpMessagingTemplate template;

    public void sendMessage(MessageDto message) {
    System.out.println(message.texto());
        template.convertAndSend("/topic/message", message.texto());
    }

    public void sendPrivateMessage(String usuarioNome, MessageDto messageDto) {
        System.out.println(messageDto.texto());
        String path = String.format("/topic/%s/message", usuarioNome);
        template.convertAndSend(path, messageDto);

    }
}
