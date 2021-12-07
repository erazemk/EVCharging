package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.services.beans.LocationBean;
import si.fri.prpo.skupina00.evcharging.services.beans.StationManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.LocationDto;

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

    @Inject
    private StationManagerBean stationManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get locations", description = "Returns all locations")
    @APIResponses({
            @APIResponse(description = "All locations", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LocationDto.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned locations")
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
    @Operation(summary = "Get location", description = "Returns a location")
    @APIResponses({
            @APIResponse(description = "Returned location", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LocationDto.class))),
            @APIResponse(description = "Failed to find location", responseCode = "403")})
    @Path("/{id}")
    public Response getLocation(@PathParam("id") Integer id) {
        LocationDto locationDto = stationManagerBean.getLocation(id);

        if (locationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(locationDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add location", description = "Adds a location")
    @APIResponses({
            @APIResponse(description = "Added location", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LocationDto.class))),
            @APIResponse(description = "Failed to add location", responseCode = "403")
    })
    public Response addLocation(LocationDto locationDto) {
        LocationDto addedLocationDto = stationManagerBean.addLocation(locationDto);

        if (addedLocationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedLocationDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update location", description = "Updates a location")
    @APIResponses({
            @APIResponse(description = "Updated location", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LocationDto.class))),
            @APIResponse(description = "Failed to update location", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateLocation(@PathParam("id") Integer id, LocationDto locationDto) {
        LocationDto updatedLocationDto = stationManagerBean.updateLocation(id, locationDto);

        if (updatedLocationDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedLocationDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete location", description = "Deletes a location")
    @APIResponses({
            @APIResponse(description = "Deleted location", responseCode = "200"),
            @APIResponse(description = "Failed to delete location.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Integer id) {
        if (stationManagerBean.deleteLocation(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
