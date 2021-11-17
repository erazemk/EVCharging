package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.Reservation;
import si.fri.prpo.skupina00.evcharging.services.beans.ReservationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ReservationsResource {

    @Inject
    ReservationBean reservationBean;

    @GET
    public Response getReservations() {
        List<Reservation> reservationList = reservationBean.getReservations();
        Response response;

        if (!reservationList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(reservationList).build();
        } else {
            // Internal error - can't retrieve reservations from database
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Path("{id}")
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
}
