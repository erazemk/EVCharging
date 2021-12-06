package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.entities.Charge;
import si.fri.prpo.skupina00.evcharging.services.beans.ChargeBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/charges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ChargesResource {

    @Inject
    private ChargeBean chargeBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get all charges.", description = "Returns all charges.")
    @APIResponses({
            @APIResponse(description = "All charges.", responseCode = "200",
                content = @Content(schema = @Schema(implementation = Charge.class, type = SchemaType.ARRAY)),
                headers = {
                    @Header(name = "X-Total-Count", description = "Number of returned charges.")
                })
    })
    public Response getCharges() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(chargeBean.getCharges(queryParameters))
                .header("X-Total-Count", chargeBean.getChargeCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get charges for user.", description = "Returns all charges for a specified user.")
    @APIResponses({
            @APIResponse(description = "All charges for user.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Charge.class))
    )})
    @Path("/{id}")
    public Response getCharge(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(chargeBean.getCharge(id))
                .build();
    }

    @POST
    @Operation(summary = "Add a charge.", description = "Adds a charge to the table.")
    @APIResponses({
            @APIResponse(description = "Add a charge.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Charge.class))),
            @APIResponse(description = "Failed to add a charge.", responseCode = "403")
            })
    public Response addCharge(Charge charge) {
        if (chargeBean.addCharge(charge)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update a charge.", description = "Updates the specified charge.")
    @APIResponses({
            @APIResponse(description = "Update charge.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Charge.class))),
            @APIResponse(description = "Failed to update a charge.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateCharge(@PathParam("id") Integer id, Charge charge) {
        if (chargeBean.updateCharge(id, charge)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete charge.", description = "Deletes the specified charge.")
    @APIResponses({
            @APIResponse(description = "Delete charge.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Charge.class))),
            @APIResponse(description = "Failed to delete charge.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteCharge(@PathParam("id") Integer id) {
        if (chargeBean.deleteCharge(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
