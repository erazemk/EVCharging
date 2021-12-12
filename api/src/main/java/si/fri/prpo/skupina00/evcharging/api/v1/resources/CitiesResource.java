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
import si.fri.prpo.skupina00.evcharging.services.beans.CityBean;
import si.fri.prpo.skupina00.evcharging.services.beans.StationManagerBean;
import si.fri.prpo.skupina00.evcharging.services.dtos.CityDto;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/cities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Secure
public class CitiesResource {

    @Inject
    private CityBean cityBean;

    @Inject
    private StationManagerBean stationManagerBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get cities", description = "Returns list of cities")
    @APIResponses({
            @APIResponse(description = "All cities", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CityDto.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned cities")
                    })
    })
    @PermitAll
    public Response getCities() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        return Response
                .status(Response.Status.OK)
                .entity(stationManagerBean.getCities(queryParameters))
                .header("X-Total-Count", cityBean.getCityCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get city", description = "Returns a city")
    @APIResponses({
            @APIResponse(description = "Returned city", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CityDto.class))),
            @APIResponse(description = "Failed to find city", responseCode = "403")})
    @Path("/{id}")
    @PermitAll
    public Response getCity(@PathParam("id") Integer id) {
        CityDto cityDto = stationManagerBean.getCity(id);

        if (cityDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(cityDto)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Operation(summary = "Add city", description = "Adds a city")
    @APIResponses({
            @APIResponse(description = "Added city", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CityDto.class))),
            @APIResponse(description = "Failed to add city", responseCode = "403")
    })
    @RolesAllowed({"admin"})
    public Response addCity(CityDto cityDto) {
        CityDto addedCityDto = stationManagerBean.addCity(cityDto);

        if (addedCityDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(addedCityDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Operation(summary = "Update city", description = "Updates a city")
    @APIResponses({
            @APIResponse(description = "Updated city", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CityDto.class))),
            @APIResponse(description = "Failed to update city", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Response updateCity(@PathParam("id") Integer id, CityDto cityDto) {
        CityDto updatedCityDto = stationManagerBean.updateCity(id, cityDto);

        if (updatedCityDto != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedCityDto)
                    .build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Operation(summary = "Delete city", description = "Deletes a city")
    @APIResponses({
            @APIResponse(description = "Delete city", responseCode = "200"),
            @APIResponse(description = "Failed to delete city", responseCode = "403")
    })
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Response deleteCity(@PathParam("id") Integer id) {
        if (stationManagerBean.deleteCity(id)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
