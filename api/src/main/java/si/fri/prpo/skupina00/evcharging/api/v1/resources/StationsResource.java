package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.entities.Station;
import si.fri.prpo.skupina00.evcharging.services.beans.StationBean;

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
public class StationsResource {

    @Inject
    private StationBean stationBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get all stations.", description = "Returns all stations.")
    @APIResponses({
            @APIResponse(description = "All stations.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Station.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned stations.")
                    })
    })
    public Response getStations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(stationBean.getStations(queryParameters))
                .header("X-Total-Count", stationBean.getStationCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get station.", description = "Returns specified station.")
    @APIResponses({
            @APIResponse(description = "Specified station.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Station.class))
            )})
    @Path("/{id}")
    public Response getStation(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(stationBean.getStation(id))
                .build();
    }

    @POST
    @Operation(summary = "Add station.", description = "Adds station to the table.")
    @APIResponses({
            @APIResponse(description = "Add station.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Station.class))),
            @APIResponse(description = "Failed to add station.", responseCode = "403")
    })
    public Response addStation(Station station) {
        if (stationBean.addStation(station)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update station.", description = "Updates the specified station.")
    @APIResponses({
            @APIResponse(description = "Update station.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Station.class))),
            @APIResponse(description = "Failed to update station.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateStation(@PathParam("id") Integer id, Station station) {
        if (stationBean.updateStation(id, station)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete station.", description = "Deletes the specified station.")
    @APIResponses({
            @APIResponse(description = "Delete station.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Station.class))),
            @APIResponse(description = "Failed to delete station.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteStation(@PathParam("id") Integer id) {
        if (stationBean.deleteStation(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
