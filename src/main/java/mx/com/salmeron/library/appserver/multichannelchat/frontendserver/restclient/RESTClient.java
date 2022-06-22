package mx.com.salmeron.library.appserver.multichannelchat.frontendserver.restclient;

import com.fasterxml.jackson.core.util.JacksonFeature;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mx.com.salmeron.library.appserver.multichannelchat.frontendserver.security.beans.Credentials;

import java.io.Serializable;

@Dependent
public class RESTClient implements Serializable {

    Client client;

    @PostConstruct
    public void Initialize() {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
    }

    public String Autorize(Credentials credentials) {


        try (Response response = client.target("http://localhost:8080/MultiChannelChatApp/auth")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(credentials))) {

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String token =  response.readEntity(String.class);
            return token;

        }


    }

}
