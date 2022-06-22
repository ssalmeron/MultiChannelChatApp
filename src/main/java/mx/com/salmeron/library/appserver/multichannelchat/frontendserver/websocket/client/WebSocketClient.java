package mx.com.salmeron.library.appserver.multichannelchat.frontendserver.websocket.client;

import jakarta.enterprise.context.Dependent;
import jakarta.websocket.ClientEndpoint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Dependent
public class WebSocketClient implements Serializable {

    private String info = "Info";




}
