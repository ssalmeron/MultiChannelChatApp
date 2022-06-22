package mx.com.salmeron.library.appserver.multichannelchat.backendserver.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


/**
 * CDI producer for {@link ObjectMapper}.
 *
 * @author cassiomolin
 */
@ApplicationScoped
public class ObjectMapperProducer {

    @Produces
    public ObjectMapper produceObjectMapper() {
        return ObjectMapperFactory.get();
    }
}
