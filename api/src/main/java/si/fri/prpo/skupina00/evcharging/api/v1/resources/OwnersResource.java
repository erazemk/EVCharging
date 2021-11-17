package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.Owner;
import si.fri.prpo.skupina00.evcharging.services.beans.OwnerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnersResource {

    @Inject
    private OwnerBean ownerBean;

    @GET
    public Response getOwners() {
        List<Owner> ownerList = ownerBean.getOwners();
        Response response;

        if (!ownerList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(ownerList).build();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GET
    @Path("{id}")
    public Response getOwner(@PathParam("id") Integer id) {
        Owner owner = ownerBean.getOwner(id);
        Response response;

        if (owner != null) {
            response = Response.status(Response.Status.OK).entity(owner).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @POST
    public Response addOwner(Owner owner) {
        Response response;

        if (ownerBean.addOwner(owner)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @PUT
    @Path("{id}")
    public Response updateOwner(@PathParam("id") Integer id, Owner owner) {
        Response response;

        if (ownerBean.updateOwner(id, owner)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteOwner(@PathParam("id") Integer id) {
        Response response;

        if (ownerBean.deleteOwner(id)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }
}
