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

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnersResource {

    @Inject
    private OwnerBean ownerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getOwners() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(ownerBean.getOwners(queryParameters))
                .header("X-Total-Count", ownerBean.getOwnerCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getOwner(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(ownerBean.getOwner(id))
                .build();
    }

    @POST
    public Response addOwner(Owner owner) {
        if (ownerBean.addOwner(owner)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateOwner(@PathParam("id") Integer id, Owner owner) {
        if (ownerBean.updateOwner(id, owner)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOwner(@PathParam("id") Integer id) {
        if (ownerBean.deleteOwner(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
