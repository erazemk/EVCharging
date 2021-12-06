package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.entities.Reservation;
import si.fri.prpo.skupina00.evcharging.services.beans.ReservationBean;

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
public class ReservationsResource {

    @Inject
    private ReservationBean reservationBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get all reservations.", description = "Returns all reservations.")
    @APIResponses({
            @APIResponse(description = "All reservations.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Reservation.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned reservations.")
                    })
    })
    public Response getReservations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(reservationBean.getReservations(queryParameters))
                .header("X-Total-Count", reservationBean.getReservationCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get reservation.", description = "Returns specified reservation.")
    @APIResponses({
            @APIResponse(description = "Specified reservation.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Reservation.class))
            )})
    @Path("/{id}")
    public Response getReservation(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(reservationBean.getReservation(id))
                .build();
    }

    @POST
    @Operation(summary = "Add reservation.", description = "Adds reservation to the table.")
    @APIResponses({
            @APIResponse(description = "Add reservation.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Reservation.class))),
            @APIResponse(description = "Failed to add reservation.", responseCode = "403")
    })
    public Response addReservation(Reservation reservation) {
        if (reservationBean.addReservation(reservation)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update reservation.", description = "Updates the specified reservation.")
    @APIResponses({
            @APIResponse(description = "Update reservation.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Reservation.class))),
            @APIResponse(description = "Failed to update reservation.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateReservation(@PathParam("id") Integer id, Reservation reservation) {
        if (reservationBean.updateReservation(id, reservation)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete reservation.", description = "Deletes the specified reservation.")
    @APIResponses({
            @APIResponse(description = "Delete reservation.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Reservation.class))),
            @APIResponse(description = "Failed to delete reservation.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Integer id) {
        if (reservationBean.deleteReservation(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
