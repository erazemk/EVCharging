package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.Charge;
import si.fri.prpo.skupina00.evcharging.services.beans.ChargeBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/charges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ChargesResource {

    @Inject
    private ChargeBean chargeBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getCharges() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(chargeBean.getCharges(queryParameters))
                .header("X-Total-Count", chargeBean.getChargeCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getCharge(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(chargeBean.getCharge(id))
                .build();
    }

    @POST
    public Response addCharge(Charge charge) {
        if (chargeBean.addCharge(charge)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCharge(@PathParam("id") Integer id, Charge charge) {
        if (chargeBean.updateCharge(id, charge)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCharge(@PathParam("id") Integer id) {
        if (chargeBean.deleteCharge(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
