package mx.com.salmeron.library.appserver.multichannelchat.backendserver.websocket.codec;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.WebSocketMessage;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.util.ObjectMapperFactory;


/**
 * Encoder for {@link WebSocketMessage}.
 *
 * @author cassiomolin
 */
public class MessageEncoder implements Encoder.Text<WebSocketMessage> {

    private final ObjectMapper mapper = ObjectMapperFactory.get();

    @Override
    public void init(EndpointConfig ec) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(WebSocketMessage message) throws EncodeException {
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new EncodeException(message, e.getMessage(), e);
        }
    }
}