package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.User;
import si.fri.prpo.skupina00.evcharging.services.beans.UserBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsersResource {

    @Inject
    UserBean userBean;

    @GET
    public Response getUsers() {
        List<User> userList = userBean.getUsers();
        Response response;

        if (!userList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(userList).build();
        } else {
            // Internal error - can't retrieve users from database
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Path("{id}")
    public Response getUser(@PathParam("id") Integer id) {
        User user = userBean.getUser(id);
        Response response;

        if (user != null) {
            response = Response.status(Response.Status.OK).entity(user).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }
}
