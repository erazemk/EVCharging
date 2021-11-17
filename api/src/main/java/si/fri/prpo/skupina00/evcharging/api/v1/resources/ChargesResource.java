package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.Charge;
import si.fri.prpo.skupina00.evcharging.services.beans.ChargeBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("charges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ChargesResource {

    @Inject
    private ChargeBean chargeBean;

    @GET
    public Response getCharges() {
        List<Charge> chargeList = chargeBean.getCharges();
        Response response;

        if (!chargeList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(chargeList).build();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GET
    @Path("{id}")
    public Response getCharge(@PathParam("id") Integer id) {
        Charge charge = chargeBean.getCharge(id);
        Response response;

        if (charge != null) {
            response = Response.status(Response.Status.OK).entity(charge).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @POST
    public Response addCharge(Charge charge) {
        Response response;

        if (chargeBean.addCharge(charge)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @PUT
    @Path("{id}")
    public Response updateCharge(@PathParam("id") Integer id, Charge charge) {
        Response response;

        if (chargeBean.updateCharge(id, charge)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteCharge(@PathParam("id") Integer id) {
        Response response;

        if (chargeBean.deleteCharge(id)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }
}
