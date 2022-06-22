package mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.payload;




import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.Payload;

import java.util.Set;

/**
 * Represents the payload of a WebSocket frame to broadcast the available users.
 *
 * @author cassiomolin
 */
public class BroadcastAvailableUsersPayload implements Payload {

    public static final String TYPE = "broadcastAvailableUsers";

    private Set<String> usernames;

    public BroadcastAvailableUsersPayload() {

    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(Set<String> usernames) {
        this.usernames = usernames;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
