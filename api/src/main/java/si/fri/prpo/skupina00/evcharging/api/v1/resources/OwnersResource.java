package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.services.beans.OwnerBean;
import si.fri.prpo.skupina00.evcharging.services.beans.UserManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.OwnerDto;

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

    @Inject
    private UserManagerBean userManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get owners", description = "Returns list of owners")
    @APIResponses({
            @APIResponse(description = "All owners", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OwnerDto.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned owner")
                    })
    })
    public Response getOwners() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        return Response
                .status(Response.Status.OK)
                .entity(userManagerBean.getOwners(queryParameters))
                .header("X-Total-Count", ownerBean.getOwnerCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get owner", description = "Returns an owner")
    @APIResponses({
            @APIResponse(description = "Returned owner", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OwnerDto.class))),
            @APIResponse(description = "Failed to find owner", responseCode = "403")})
    @Path("/{id}")
    public Response getOwner(@PathParam("id") Integer id) {
        OwnerDto ownerDto = userManagerBean.getOwner(id);

        if (ownerDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(ownerDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add owner", description = "Adds an owner")
    @APIResponses({
            @APIResponse(description = "Added owner", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OwnerDto.class))),
            @APIResponse(description = "Failed to add owner", responseCode = "403")
    })
    public Response addOwner(OwnerDto ownerDto) {
        OwnerDto addedOwnerDto = userManagerBean.addOwner(ownerDto);

        if (addedOwnerDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedOwnerDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update owner", description = "Updates an owner")
    @APIResponses({
            @APIResponse(description = "Updated owner", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OwnerDto.class))),
            @APIResponse(description = "Failed to update owner", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateOwner(@PathParam("id") Integer id, OwnerDto ownerDto) {
        OwnerDto updatedOwnerDto = userManagerBean.updateOwner(id, ownerDto);

        if (updatedOwnerDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedOwnerDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete owner", description = "Deletes an owner")
    @APIResponses({
            @APIResponse(description = "Deleted owner", responseCode = "200"),
            @APIResponse(description = "Failed to delete owner", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteOwner(@PathParam("id") Integer id) {
        if (userManagerBean.deleteOwner(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
