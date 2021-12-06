package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
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
    public Response getCities() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(cityBean.getCities(queryParameters))
                .header("X-Total-Count", cityBean.getCityCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getCity(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(cityBean.getCity(id))
                .build();
    }

    @POST
    public Response addCity(City city) {
        if (cityBean.addCity(city)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCity(@PathParam("id") Integer id, City city) {
        if (cityBean.updateCity(id, city)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCity(@PathParam("id") Integer id) {
        if (cityBean.deleteCity(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
