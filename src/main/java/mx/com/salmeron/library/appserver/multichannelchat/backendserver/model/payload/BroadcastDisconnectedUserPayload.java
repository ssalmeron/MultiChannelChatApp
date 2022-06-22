package mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.payload;


import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.Payload;

/**
 * Represents the payload of a WebSocket frame to broadcast details of a user who has connected to the chat.
 *
 * @author cassiomolin
 */
public class BroadcastDisconnectedUserPayload implements Payload {

    public static final String TYPE = "broadcastDisconnectedUser";

    private String username;

    public BroadcastDisconnectedUserPayload() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
