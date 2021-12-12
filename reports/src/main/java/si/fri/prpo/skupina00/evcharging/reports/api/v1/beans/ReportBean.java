package si.fri.prpo.skupina00.evcharging.reports.api.v1.beans;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.skupina00.evcharging.reports.api.v1.dtos.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ReportBean {
    private static final Logger log = Logger.getLogger(ReportBean.class.getName());

    private Client httpClient;
    private String baseUrl;
    private String apiKey;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance()
                .get("integrations.main.base-url")
                .orElse("http://localhost:8080/v1");
        apiKey = ConfigurationUtil.getInstance()
                .get("secrets.api-key")
                .orElse("");
    }

    public List<UserDto> getUsers() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/users")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public List<OwnerDto> getOwners() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/owners")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public List<StationDto> getStations() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/stations")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public List<LocationDto> getLocations() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/locations")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public List<CityDto> getCities() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/cities")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public List<ChargeDto> getCharges() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/charges")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }

    public List<ReservationDto> getReservations() {
        try {
            return httpClient
                    .target(baseUrl)
                    .path("/reservations")
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .get(new GenericType<>(){});
        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
}
