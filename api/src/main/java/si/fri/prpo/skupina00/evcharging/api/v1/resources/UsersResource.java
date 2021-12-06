package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
    @Operation(summary = "Get all users.", description = "Returns all users.")
    @APIResponses({
            @APIResponse(description = "All users.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned users.")
                    })
    })
    public Response getUsers() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(userBean.getUsers(queryParameters))
                .header("X-Total-Count", userBean.getUserCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get user.", description = "Returns specified user.")
    @APIResponses({
            @APIResponse(description = "Specified user.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))
            )})
    @Path("/{id}")
    public Response getUser(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(userBean.getUser(id))
                .build();
    }

    @POST
    @Operation(summary = "Add user.", description = "Adds user to the table.")
    @APIResponses({
            @APIResponse(description = "Add user.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @APIResponse(description = "Failed to add user.", responseCode = "403")
    })
    public Response addUser(User user) {
        if (userBean.addUser(user)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update user.", description = "Updates the specified user.")
    @APIResponses({
            @APIResponse(description = "Update user.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @APIResponse(description = "Failed to update user.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Integer id, User user) {
        if (userBean.updateUser(id, user)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete user.", description = "Deletes the specified user.")
    @APIResponses({
            @APIResponse(description = "Delete user.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @APIResponse(description = "Failed to delete user.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        if (userBean.deleteUser(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
