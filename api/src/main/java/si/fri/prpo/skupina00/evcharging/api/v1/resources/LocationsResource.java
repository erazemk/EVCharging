package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.entities.Location;
import si.fri.prpo.skupina00.evcharging.services.beans.LocationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LocationsResource {

    @Inject
    private LocationBean locationBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get all locations.", description = "Returns all locations.")
    @APIResponses({
            @APIResponse(description = "All locations.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Location.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned locations.")
                    })
    })
    public Response getLocations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(locationBean.getLocations(queryParameters))
                .header("X-Total-Count", locationBean.getLocationCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get location.", description = "Returns specified location.")
    @APIResponses({
            @APIResponse(description = "Specified location.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Location.class))
            )})
    @Path("/{id}")
    public Response getLocation(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(locationBean.getLocation(id))
                .build();
    }

    @POST
    @Operation(summary = "Add a location.", description = "Adds a location to the table.")
    @APIResponses({
            @APIResponse(description = "Add a location.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @APIResponse(description = "Failed to add a location.", responseCode = "403")
    })
    public Response addLocation(Location location) {
        if (locationBean.addLocation(location)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update a location.", description = "Updates the specified location.")
    @APIResponses({
            @APIResponse(description = "Update location.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @APIResponse(description = "Failed to update a location.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateLocation(@PathParam("id") Integer id, Location location) {
        if (locationBean.updateLocation(id, location)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete location.", description = "Deletes the specified location.")
    @APIResponses({
            @APIResponse(description = "Delete location.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @APIResponse(description = "Failed to delete location.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Integer id) {
        if (locationBean.deleteLocation(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
