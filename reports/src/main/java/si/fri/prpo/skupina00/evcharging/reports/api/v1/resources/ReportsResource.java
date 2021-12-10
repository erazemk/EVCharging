package si.fri.prpo.skupina00.evcharging.reports.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.reports.api.v1.beans.ReportBean;
import si.fri.prpo.skupina00.evcharging.reports.api.v1.dtos.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("reports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ReportsResource {

    @Inject
    private ReportBean reportBean;

    @GET
    @Operation(summary = "Get report", description = "Returns a system report")
    @APIResponses({
            @APIResponse(description = "Generated report", responseCode = "200",
                content = @Content(schema = @Schema(implementation = ReportDto.class))
            )
    })
    public Response getReport() {
        ReportDto reportDto = new ReportDto();

        List<UserDto> users = reportBean.getUsers();
        List<OwnerDto> owners = reportBean.getOwners();
        List<StationDto> stations = reportBean.getStations();
        List<CityDto> cities = reportBean.getCities();
        List<LocationDto> locations = reportBean.getLocations();
        List<ChargeDto> charges = reportBean.getCharges();
        List<ReservationDto> reservations = reportBean.getReservations();

        Float minPrice = Float.MAX_VALUE, maxPrice = Float.MIN_VALUE;

        for (StationDto stationDto : stations) {
            if (stationDto.getPrice() < minPrice) {
                minPrice = stationDto.getPrice();
            }

            if (stationDto.getPrice() > maxPrice) {
                maxPrice = stationDto.getPrice();
            }
        }

        reportDto.setUsers(users.size());
        reportDto.setOwners(owners.size());
        reportDto.setStations(stations.size());
        reportDto.setCities(cities.size());
        reportDto.setLocations(locations.size());
        reportDto.setCharges(charges.size());
        reportDto.setReservations(reservations.size());
        reportDto.setMinPrice(minPrice);
        reportDto.setMaxPrice(maxPrice);

        return Response
                .status(Response.Status.OK)
                .entity(reportDto)
                .build();
    }
}
