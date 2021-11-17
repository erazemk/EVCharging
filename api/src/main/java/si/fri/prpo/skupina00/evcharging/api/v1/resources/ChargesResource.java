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
    ChargeBean chargeBean;

    @GET
    public Response getCharges() {
        List<Charge> chargeList = chargeBean.getCharges();
        Response response;

        if (!chargeList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(chargeList).build();
        } else {
            // Internal error - can't retrieve charges from database
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

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
}
