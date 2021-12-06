package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.Reservation;
import si.fri.prpo.skupina00.evcharging.services.beans.ReservationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

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
    public Response getReservations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Reservation> reservationList = reservationBean.getReservations(queryParameters);
        Response response;

        if (!reservationList.isEmpty()) {
            response = Response
                    .status(Response.Status.OK)
                    .entity(reservationList)
                    .header("X-Total-Count", reservationBean.getReservationCount(queryParameters))
                    .build();
        } else {
            response = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }

        return response;
    }

    @GET
    @Path("/{id}")
    public Response getReservation(@PathParam("id") Integer id) {
        Reservation reservation = reservationBean.getReservation(id);
        Response response;

        if (reservation != null) {
            response = Response.status(Response.Status.OK).entity(reservation).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @POST
    public Response addReservation(Reservation reservation) {
        Response response;

        if (reservationBean.addReservation(reservation)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @PUT
    @Path("/{id}")
    public Response updateReservation(@PathParam("id") Integer id, Reservation reservation) {
        Response response;

        if (reservationBean.updateReservation(id, reservation)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Integer id) {
        Response response;

        if (reservationBean.deleteReservation(id)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }
}
