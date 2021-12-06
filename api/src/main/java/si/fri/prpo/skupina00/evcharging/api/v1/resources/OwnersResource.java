package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
    @Operation(summary = "Get all owners.", description = "Returns all owners.")
    @APIResponses({
            @APIResponse(description = "All owners.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Owner.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned owner.")
                    })
    })
    public Response getOwners() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(ownerBean.getOwners(queryParameters))
                .header("X-Total-Count", ownerBean.getOwnerCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get owner.", description = "Returns specified owner.")
    @APIResponses({
            @APIResponse(description = "Specified owner.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Owner.class))
            )})
    @Path("/{id}")
    public Response getOwner(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(ownerBean.getOwner(id))
                .build();
    }

    @POST
    @Operation(summary = "Add owner.", description = "Adds owner to the table.")
    @APIResponses({
            @APIResponse(description = "Add owner.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Owner.class))),
            @APIResponse(description = "Failed to add owner.", responseCode = "403")
    })
    public Response addOwner(Owner owner) {
        if (ownerBean.addOwner(owner)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update owner.", description = "Updates the specified owner.")
    @APIResponses({
            @APIResponse(description = "Update owner.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Owner.class))),
            @APIResponse(description = "Failed to update owner.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateOwner(@PathParam("id") Integer id, Owner owner) {
        if (ownerBean.updateOwner(id, owner)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete owner.", description = "Deletes the specified owner.")
    @APIResponses({
            @APIResponse(description = "Delete owner.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Owner.class))),
            @APIResponse(description = "Failed to delete owner.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteOwner(@PathParam("id") Integer id) {
        if (ownerBean.deleteOwner(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
