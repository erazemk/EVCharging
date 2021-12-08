package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.services.beans.UserBean;
import si.fri.prpo.skupina00.evcharging.services.beans.UserManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.UserDto;

import javax.annotation.security.RolesAllowed;
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
@Secure
public class UsersResource {

    @Inject
    private UserBean userBean;

    @Inject
    private UserManagerBean userManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get users", description = "Returns all users")
    @APIResponses({
            @APIResponse(description = "All users", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned users")
                    })
    })
    @RolesAllowed("admin")
    public Response getUsers() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        return Response
                .status(Response.Status.OK)
                .entity(userManagerBean.getUsers(queryParameters))
                .header("X-Total-Count", userBean.getUserCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get user", description = "Returns a user")
    @APIResponses({
            @APIResponse(description = "Returned user", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @APIResponse(description = "Failed to find user", responseCode = "403")})
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response getUser(@PathParam("id") Integer id) {
        UserDto userDto = userManagerBean.getUser(id);

        if (userDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(userDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add user", description = "Adds a user")
    @APIResponses({
            @APIResponse(description = "Added user", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @APIResponse(description = "Failed to add user", responseCode = "403")
    })
    @RolesAllowed("admin")
    public Response addUser(UserDto userDto) {
        UserDto addedUserDto = userManagerBean.addUser(userDto);

        if (addedUserDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedUserDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update user", description = "Updates a user")
    @APIResponses({
            @APIResponse(description = "Updated user", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @APIResponse(description = "Failed to update user", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed("user")
    public Response updateUser(@PathParam("id") Integer id, UserDto userDto) {
        UserDto updatedUserDto = userManagerBean.updateUser(id, userDto);

        if (updatedUserDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedUserDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete user", description = "Deletes a user")
    @APIResponses({
            @APIResponse(description = "Deleted user", responseCode = "200"),
            @APIResponse(description = "Failed to delete user", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response deleteUser(@PathParam("id") Integer id) {
        if (userManagerBean.deleteUser(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
