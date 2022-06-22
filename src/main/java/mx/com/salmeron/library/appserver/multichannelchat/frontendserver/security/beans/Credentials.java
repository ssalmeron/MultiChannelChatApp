package mx.com.salmeron.library.appserver.multichannelchat.frontendserver.security.beans;

import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@RequestScoped
public class Credentials {
    private String username;
    private String password;

}
