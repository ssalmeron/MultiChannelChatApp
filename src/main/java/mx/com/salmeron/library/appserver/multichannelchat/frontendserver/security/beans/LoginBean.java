package mx.com.salmeron.library.appserver.multichannelchat.frontendserver.security.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.com.salmeron.library.appserver.multichannelchat.frontendserver.restclient.RESTClient;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;

@Getter
@Setter
@RequestScoped
@NoArgsConstructor
@Named("loginBean")
public class LoginBean implements Serializable {

    @Serial
    private static final long serialVersionUID = -5484143018053490203L;

    private String username;
    private String password;

    @Inject
    private Credentials credentials;

    @Inject
    private RESTClient client;




    public void doLogin(){
        credentials.setUsername(username);
        credentials.setPassword(password);

        String token = client.Autorize(credentials);
        System.out.println("Auth: " + credentials.toString() + "toke: " + token);


    }
}
