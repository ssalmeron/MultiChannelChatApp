package mx.com.salmeron.library.appserver.multichannelchat.backendserver.websocket.codec;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.WebSocketMessage;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.util.ObjectMapperFactory;


import java.io.IOException;

/**
 * Decoder for {@link WebSocketMessage}.
 *
 * @author cassiomolin
 */
public class MessageDecoder implements Decoder.Text<WebSocketMessage> {

    private final ObjectMapper mapper = ObjectMapperFactory.get();

    @Override
    public void init(EndpointConfig ec) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public WebSocketMessage decode(String s) throws DecodeException {
        try {
            return mapper.readValue(s, WebSocketMessage.class);
        } catch (IOException e) {
            throw new DecodeException(s, e.getMessage(), e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }
}