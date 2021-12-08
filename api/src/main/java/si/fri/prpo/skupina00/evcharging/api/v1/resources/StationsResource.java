package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.services.beans.StationBean;
import si.fri.prpo.skupina00.evcharging.services.beans.StationManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.StationDto;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Secure
public class StationsResource {

    @Inject
    private StationBean stationBean;

    @Inject
    private StationManagerBean stationManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get stations", description = "Returns all stations")
    @APIResponses({
            @APIResponse(description = "All stations", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = StationDto.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned stations")
                    })
    })
    @PermitAll
    public Response getStations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        return Response
                .status(Response.Status.OK)
                .entity(stationManagerBean.getStations(queryParameters))
                .header("X-Total-Count", stationBean.getStationCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get station", description = "Returns a station")
    @APIResponses({
            @APIResponse(description = "Returned station", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = StationDto.class))),
            @APIResponse(description = "Failed to find station", responseCode = "403")})
    @Path("/{id}")
    @PermitAll
    public Response getStation(@PathParam("id") Integer id) {
        StationDto stationDto = stationManagerBean.getStation(id);

        if (stationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(stationDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add station", description = "Adds a station")
    @APIResponses({
            @APIResponse(description = "Added station", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = StationDto.class))),
            @APIResponse(description = "Failed to add station", responseCode = "403")
    })
    @RolesAllowed("owner")
    public Response addStation(StationDto stationDto) {
        StationDto addedStationDto = stationManagerBean.addStation(stationDto);

        if (addedStationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedStationDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update station", description = "Updates a station")
    @APIResponses({
            @APIResponse(description = "Updated station", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = StationDto.class))),
            @APIResponse(description = "Failed to update station", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed("owner")
    public Response updateStation(@PathParam("id") Integer id, StationDto stationDto) {
        StationDto updatedStationDto = stationManagerBean.updateStation(id, stationDto);

        if (updatedStationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedStationDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete station", description = "Deletes a station")
    @APIResponses({
            @APIResponse(description = "Deleted station", responseCode = "200"),
            @APIResponse(description = "Failed to delete station", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed("owner")
    public Response deleteStation(@PathParam("id") Integer id) {
        if (stationManagerBean.deleteStation(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
