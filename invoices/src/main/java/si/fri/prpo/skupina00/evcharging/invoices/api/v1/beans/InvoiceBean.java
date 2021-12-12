package si.fri.prpo.skupina00.evcharging.invoices.api.v1.beans;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.ChargeDto;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.UserDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@ApplicationScoped
public class InvoiceBean {
    private static final Logger log = Logger.getLogger(InvoiceBean.class.getName());

    private Client httpClient;
    private String baseUrl;
    private String apiKey;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8080/v1";
        apiKey = ConfigurationUtil.getInstance()
                .get("secrets.api-key")
                .orElse("");
    }

    public UserDto getUser(Integer id) {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/users/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .header("authorization", "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public ChargeDto getCharge(Integer id) {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/charges/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .header("authorization", "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
}
