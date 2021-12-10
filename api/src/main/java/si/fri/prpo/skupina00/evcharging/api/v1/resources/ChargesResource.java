package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.services.beans.ChargeBean;
import si.fri.prpo.skupina00.evcharging.services.beans.StationManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.ChargeDto;

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
//@Secure
public class ChargesResource {

    @Inject
    private ChargeBean chargeBean;

    @Inject
    private StationManagerBean stationManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get charges", description = "Returns list of charges")
    @APIResponses({
            @APIResponse(description = "All charges", responseCode = "200",
                content = @Content(schema = @Schema(implementation = ChargeDto.class, type = SchemaType.ARRAY)),
                headers = { @Header(name = "X-Total-Count", description = "Number of returned charges") })
    })
    //@RolesAllowed({"user"})
    public Response getCharges() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        return Response
                .status(Response.Status.OK)
                .entity(stationManagerBean.getCharges(queryParameters))
                .header("X-Total-Count", chargeBean.getChargeCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get charge", description = "Returns a charge")
    @APIResponses({
            @APIResponse(description = "Returned charge", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ChargeDto.class))),
            @APIResponse(description = "Failed to find charge", responseCode = "403")})
    @Path("/{id}")
    //@RolesAllowed("user")
    public Response getCharge(@PathParam("id") Integer id) {
        ChargeDto chargeDto = stationManagerBean.getCharge(id);

        if (chargeDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(chargeDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add charge", description = "Adds a charge")
    @APIResponses({
            @APIResponse(description = "Added charge", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ChargeDto.class))),
            @APIResponse(description = "Failed to add charge", responseCode = "403")
            })
    //@RolesAllowed("user")
    public Response addCharge(ChargeDto chargeDto) {
        ChargeDto addedChargeDto = stationManagerBean.addCharge(chargeDto);

        if (addedChargeDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedChargeDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update charge", description = "Updates a charge")
    @APIResponses({
            @APIResponse(description = "Updated charge", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ChargeDto.class))),
            @APIResponse(description = "Failed to update charge", responseCode = "403")
    })
    @Path("/{id}")
    //@RolesAllowed("admin")
    public Response updateCharge(@PathParam("id") Integer id, ChargeDto chargeDto) {
        ChargeDto updatedChargeDto = stationManagerBean.updateCharge(id, chargeDto);

        if (updatedChargeDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedChargeDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete charge", description = "Deletes a charge")
    @APIResponses({
            @APIResponse(description = "Deleted charge", responseCode = "200"),
            @APIResponse(description = "Failed to delete charge", responseCode = "403")
    })
    @Path("/{id}")
    //@RolesAllowed("admin")
    public Response deleteCharge(@PathParam("id") Integer id) {
        if (stationManagerBean.deleteCharge(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
