package mx.com.salmeron.library.appserver.multichannelchat.backendserver.websocket;

import jakarta.enterprise.context.Dependent;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.WebSocketMessage;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.model.payload.*;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.websocket.codec.MessageDecoder;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.websocket.codec.MessageEncoder;
import mx.com.salmeron.library.appserver.multichannelchat.backendserver.websocket.config.CdiAwareConfigurator;




import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Dependent
@ServerEndpoint(
        value = "/chat",
        encoders = MessageEncoder.class,
        decoders = MessageDecoder.class,
        configurator = CdiAwareConfigurator.class)
public class ChatEndPoint {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        String username = session.getUserPrincipal().getName();
        welcomeUser(session, username);
        broadcastUserConnected(session, username);
        broadcastAvailableUsers();


    }



    /***********/

    private void welcomeUser(Session currentSession, String username) {
        WelcomeUserPayload payload = new WelcomeUserPayload();
        payload.setUsername(username);
        currentSession.getAsyncRemote().sendObject(new WebSocketMessage(payload));
    }

    private void broadcastUserConnected(Session currentSession, String username) {
        BroadcastConnectedUserPayload payload = new BroadcastConnectedUserPayload();
        payload.setUsername(username);
        broadcast(currentSession, new WebSocketMessage(payload));
    }

    private void broadcastUserDisconnected(String username) {
        BroadcastDisconnectedUserPayload payload = new BroadcastDisconnectedUserPayload();
        payload.setUsername(username);
        broadcast(new WebSocketMessage(payload));
    }

    private void broadcastTextMessage(String username, String text) {
        BroadcastTextMessagePayload payload = new BroadcastTextMessagePayload();
        payload.setContent(text);
        payload.setUsername(username);
        broadcast(new WebSocketMessage(payload));
    }

    private void broadcastAvailableUsers() {

        Set<String> usernames = sessions.stream()
                .map(Session::getUserPrincipal)
                .map(Principal::getName)
                .distinct()
                .collect(Collectors.toSet());

        BroadcastAvailableUsersPayload payload = new BroadcastAvailableUsersPayload();
        payload.setUsernames(usernames);
        broadcast(new WebSocketMessage(payload));
    }

    private void broadcast(WebSocketMessage message) {
        synchronized (sessions) {
            sessions.forEach(session -> {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendObject(message);
                }
            });
        }
    }

    private void broadcast(Session ignoredSession, WebSocketMessage message) {
        synchronized (sessions) {
            sessions.forEach(session -> {
                if (session.isOpen() && !session.getId().equals(ignoredSession.getId())) {
                    session.getAsyncRemote().sendObject(message);
                }
            });
        }
    }


}
