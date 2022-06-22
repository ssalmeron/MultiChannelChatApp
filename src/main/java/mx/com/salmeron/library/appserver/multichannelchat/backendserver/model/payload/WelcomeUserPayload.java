package mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.payload;


import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.Payload;

/**
 * Represents the payload of a WebSocket frame to welcome a user.
 *
 * @author cassiomolin
 */
public class WelcomeUserPayload implements Payload {

    public static final String TYPE = "welcomeUser";

    private String username;

    public WelcomeUserPayload() {

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
