package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.User;
import si.fri.prpo.skupina00.evcharging.services.beans.UserBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsersResource {

    @Inject
    private UserBean userBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getUsers() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(userBean.getUsers(queryParameters))
                .header("X-Total-Count", userBean.getUserCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(userBean.getUser(id))
                .build();
    }

    @POST
    public Response addUser(User user) {
        if (userBean.addUser(user)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Integer id, User user) {
        if (userBean.updateUser(id, user)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        if (userBean.deleteUser(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
