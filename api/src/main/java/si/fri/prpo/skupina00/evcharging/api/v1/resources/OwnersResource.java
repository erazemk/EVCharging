package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.Owner;
import si.fri.prpo.skupina00.evcharging.services.beans.OwnerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnersResource {

    private static final Logger log = Logger.getLogger(OwnersResource.class.getName());

    @Inject
    private OwnerBean ownerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getOwners() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Owner> ownerList = ownerBean.getOwners(queryParameters);
        return Response
                .status(Response.Status.OK)
                .entity(ownerList)
                .header("X-Total-Count", ownerBean.getOwnerCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
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
    @Path("/{id}")
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
    @Path("/{id}")
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
