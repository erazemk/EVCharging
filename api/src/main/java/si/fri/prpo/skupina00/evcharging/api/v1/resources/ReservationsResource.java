package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.services.beans.ReservationBean;
import si.fri.prpo.skupina00.evcharging.services.beans.StationManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.ReservationDto;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Secure
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class ReservationsResource {

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private StationManagerBean stationManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get reservations", description = "Returns all reservations")
    @APIResponses({
            @APIResponse(description = "All reservations", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReservationDto.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned reservations")
                    })
    })
    @RolesAllowed({"admin", "user"})
    public Response getReservations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        return Response
                .status(Response.Status.OK)
                .entity(stationManagerBean.getReservations(queryParameters))
                .header("X-Total-Count", reservationBean.getReservationCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get reservation", description = "Returns a reservation")
    @APIResponses({
            @APIResponse(description = "Returned reservation", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReservationDto.class))),
            @APIResponse(description = "Failed to find reservation", responseCode = "403")})
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response getReservation(@PathParam("id") Integer id) {
        ReservationDto reservationDto = stationManagerBean.getReservation(id);

        if (reservationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(reservationDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add reservation", description = "Adds a reservation")
    @APIResponses({
            @APIResponse(description = "Added reservation", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReservationDto.class))),
            @APIResponse(description = "Failed to add reservation", responseCode = "403")
    })
    @RolesAllowed({"admin", "user"})
    public Response addReservation(ReservationDto reservationDto) {
        ReservationDto addedReservationDto = stationManagerBean.addReservation(reservationDto);

        if (addedReservationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedReservationDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update reservation", description = "Updates a reservation")
    @APIResponses({
            @APIResponse(description = "Updated reservation", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReservationDto.class))),
            @APIResponse(description = "Failed to update reservation", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response updateReservation(@PathParam("id") Integer id, ReservationDto reservationDto) {
        ReservationDto updatedReservationDto = stationManagerBean.updateReservation(id, reservationDto);

        if (updatedReservationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedReservationDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete reservation", description = "Deletes a reservation")
    @APIResponses({
            @APIResponse(description = "Deleted reservation", responseCode = "200"),
            @APIResponse(description = "Failed to delete reservation", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response deleteReservation(@PathParam("id") Integer id) {
        if (stationManagerBean.deleteReservation(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
