package si.fri.prpo.skupina00.evcharging.invoices.api.v1.beans;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import org.json.JSONObject;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.ChargeDto;
import si.fri.prpo.skupina00.evcharging.invoices.api.v1.dtos.UserDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@ApplicationScoped
public class InvoiceBean {
    private static final Logger log = Logger.getLogger(InvoiceBean.class.getName());

    private Client httpClient;
    private String baseUrl, apiKey;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance()
                .get("integrations.main.base-url")
                .orElse("http://prpo.erazem.eu/v1");
        apiKey = new JSONObject(httpClient
                .target("http://do.erazem.eu:8080/auth/realms/evcharging/protocol/openid-connect/token")
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.form(new Form()
                        .param("grant_type", "password")
                        .param("client_id", "evcharging-app")
                        .param("username", "microservice")
                        .param("password", "password")
                ))
                .readEntity(String.class))
                .getString("access_token");
    }

    public UserDto getUser(Integer id) {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/users/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
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
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
}
