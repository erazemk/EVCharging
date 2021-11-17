package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.Location;
import si.fri.prpo.skupina00.evcharging.services.beans.LocationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LocationsResource {

    @Inject
    LocationBean locationBean;

    @GET
    public Response getLocations() {
        List<Location> locationList = locationBean.getLocations();
        Response response;

        if (!locationList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(locationList).build();
        } else {
            // Internal error - can't retrieve locations from database
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Path("{id}")
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
}
