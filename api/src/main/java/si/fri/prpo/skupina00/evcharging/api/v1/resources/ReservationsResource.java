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
        return Response
                .status(Response.Status.OK)
                .entity(reservationBean.getReservations(queryParameters))
                .header("X-Total-Count", reservationBean.getReservationCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getReservation(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(reservationBean.getReservation(id))
                .build();
    }

    @POST
    public Response addReservation(Reservation reservation) {
        if (reservationBean.addReservation(reservation)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateReservation(@PathParam("id") Integer id, Reservation reservation) {
        if (reservationBean.updateReservation(id, reservation)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Integer id) {
        if (reservationBean.deleteReservation(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
