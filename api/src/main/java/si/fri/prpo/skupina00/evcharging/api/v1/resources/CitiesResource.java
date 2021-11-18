package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.City;
import si.fri.prpo.skupina00.evcharging.services.beans.CityBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CitiesResource {

    @Inject
    private CityBean cityBean;

    @GET
    public Response getCities() {
        List<City> cityList = cityBean.getCities();
        Response response;

        if (!cityList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(cityList).build();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GET
    @Path("{id}")
    public Response getCity(@PathParam("id") Integer id) {
        City city = cityBean.getCity(id);
        Response response;

        if (city != null) {
            response = Response.status(Response.Status.OK).entity(city).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @POST
    public Response addCity(City city) {
        Response response;

        if (cityBean.addCity(city)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @PUT
    @Path("{id}")
    public Response updateCity(@PathParam("id") Integer id, City city) {
        Response response;

        if (cityBean.updateCity(id, city)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteCity(@PathParam("id") Integer id) {
        Response response;

        if (cityBean.deleteCity(id)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }
}