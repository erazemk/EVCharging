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
    OwnerBean ownerBean;

    @GET
    public Response getOwners() {
        List<Owner> ownerList = ownerBean.getOwners();
        Response response;

        if (!ownerList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(ownerList).build();
        } else {
            // Internal error - can't retrieve owners from database
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

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
}
