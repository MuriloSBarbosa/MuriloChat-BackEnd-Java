package murilo.barbosa.murilochat.controller;

import murilo.barbosa.murilochat.service.websocket.WebSocketService;
import murilo.barbosa.murilochat.service.websocket.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
@CrossOrigin
public class ChatController {

    @Autowired
    private WebSocketService webSocketService;

    @PostMapping
    public void sendMessage(@RequestBody MessageDto messageDto){
        System.out.println("Mensagem");
        webSocketService.sendMessage(messageDto);
    }

    @PostMapping("/{usuarioNome}")
    public void sendPrivateMessage(@PathVariable String usuarioNome, @RequestBody MessageDto messageDto) {
        webSocketService.sendPrivateMessage(usuarioNome,messageDto);
    }
}
