package si.fri.prpo.skupina00.evcharging.reports.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.reports.api.v1.beans.ReportBean;
import si.fri.prpo.skupina00.evcharging.reports.api.v1.dtos.ReportDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        reportDto.setUsers(reportBean.getEntities("/users").size());
        //reportDto.setUsers(reportBean.getUsers().size());
        reportDto.setOwners(reportBean.getOwners().size());
        reportDto.setStations(reportBean.getStations().size());
        reportDto.setCities(reportBean.getCities().size());
        reportDto.setLocations(reportBean.getLocations().size());
        reportDto.setCharges(reportBean.getCharges().size());
        reportDto.setReservations(reportBean.getReservations().size());

        return Response
                .status(Response.Status.OK)
                .entity(reportDto)
                .build();
    }
}
