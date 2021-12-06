package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina00.evcharging.entities.City;
import si.fri.prpo.skupina00.evcharging.services.beans.CityBean;

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
public class CitiesResource {

    @Inject
    private CityBean cityBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Operation(summary = "Get all cities.", description = "Returns all cities.")
    @APIResponses({
            @APIResponse(description = "All cities.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = City.class, type = SchemaType.ARRAY)),
                    headers = {
                            @Header(name = "X-Total-Count", description = "Number of returned cities.")
                    })
    })
    public Response getCities() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(cityBean.getCities(queryParameters))
                .header("X-Total-Count", cityBean.getCityCount(queryParameters))
                .build();
    }

    @GET
    @Operation(summary = "Get city.", description = "Returns specified city.")
    @APIResponses({
            @APIResponse(description = "Specified city.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = City.class))
            )})
    @Path("/{id}")
    public Response getCity(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(cityBean.getCity(id))
                .build();
    }

    @POST
    @Operation(summary = "Add city.", description = "Adds a new city to the table.")
    @APIResponses({
            @APIResponse(description = "Add a city.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = City.class))),
            @APIResponse(description = "Failed to add a city.", responseCode = "403")
    })
    public Response addCity(City city) {
        if (cityBean.addCity(city)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Operation(summary = "Update city.", description = "Updates the specified city.")
    @APIResponses({
            @APIResponse(description = "Update city.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = City.class))),
            @APIResponse(description = "Failed to update a city.", responseCode = "403")
    })
    @Path("/{id}")
    public Response updateCity(@PathParam("id") Integer id, City city) {
        if (cityBean.updateCity(id, city)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Operation(summary = "Delete city.", description = "Deletes the specified city.")
    @APIResponses({
            @APIResponse(description = "Delete city.", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = City.class))),
            @APIResponse(description = "Failed to delete city.", responseCode = "403")
    })
    @Path("/{id}")
    public Response deleteCity(@PathParam("id") Integer id) {
        if (cityBean.deleteCity(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
