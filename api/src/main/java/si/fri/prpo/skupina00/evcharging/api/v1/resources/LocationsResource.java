package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.Location;
import si.fri.prpo.skupina00.evcharging.services.beans.LocationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LocationsResource {

    @Inject
    private LocationBean locationBean;

    @GET
    public Response getLocations() {
        List<Location> locationList = locationBean.getLocations();
        Response response;

        if (!locationList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(locationList).build();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GET
    @Path("/{id}")
    public Response getLocation(@PathParam("id") Integer id) {
        Location location = locationBean.getLocation(id);
        Response response;

        if (location != null) {
            response = Response.status(Response.Status.OK).entity(location).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @POST
    public Response addLocation(Location location) {
        Response response;

        if (locationBean.addLocation(location)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @PUT
    @Path("/{id}")
    public Response updateLocation(@PathParam("id") Integer id, Location location) {
        Response response;

        if (locationBean.updateLocation(id, location)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Integer id) {
        Response response;

        if (locationBean.deleteLocation(id)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }
}
