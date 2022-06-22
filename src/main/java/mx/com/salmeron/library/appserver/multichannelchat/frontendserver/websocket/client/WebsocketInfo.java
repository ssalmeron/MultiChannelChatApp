package mx.com.salmeron.library.appserver.multichannelchat.frontendserver.websocket.client;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SessionScoped
public class WebsocketInfo implements Serializable {
    @Inject
    private WebSocketClient webSocketClient;

}
