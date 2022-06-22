package mx.com.salmeron.library.appserver.multichannelchat.backendserver.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private Authenticator authenticator;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Credentials credentials = mapper.readValue(req.getReader(), Credentials.class);

        if (authenticator.checkCredentials(credentials.getUsername(), credentials.getPassword())) {
            String token = authenticator.issueAccessToken(credentials.getUsername());
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter() , new WebSocketAccessToken(token));
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.setContentType("text/plain");
            resp.getWriter().write("Invalid credentials");
        }
    }

    /**
     * Model that holds credentials for a user.
     *
     * @author cassiomolin
     */
    private static class Credentials {

        private String username;

        private String password;

        public Credentials() {

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Model that holds an access token for the WebSocket endpoints.
     */
    private static class WebSocketAccessToken {

        private String token;

        public WebSocketAccessToken() {

        }

        public WebSocketAccessToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}