package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
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
    public Response getLocations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(locationBean.getLocations(queryParameters))
                .header("X-Total-Count", locationBean.getLocationCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getLocation(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(locationBean.getLocation(id))
                .build();
    }

    @POST
    public Response addLocation(Location location) {
        if (locationBean.addLocation(location)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateLocation(@PathParam("id") Integer id, Location location) {
        if (locationBean.updateLocation(id, location)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocation(@PathParam("id") Integer id) {
        if (locationBean.deleteLocation(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
