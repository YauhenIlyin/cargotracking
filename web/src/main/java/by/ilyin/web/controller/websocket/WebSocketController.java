package by.ilyin.web.controller.websocket;

import by.ilyin.web.dto.ReachedCheckpointInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/checkpoint-reached/{clientId}")
    public void sendReachedCheckpointInfo(@DestinationVariable Long clientId,
                                          ReachedCheckpointInfoDTO reachedCheckpointInfoDTO) {
        simpMessagingTemplate.convertAndSend("/api/waybill-notifications/" + clientId, reachedCheckpointInfoDTO);
    }

}
