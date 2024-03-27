package murilo.barbosa.murilochat.service.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
        System.out.println("user disconected");
        System.out.println(event.getSessionId());
    }
}

