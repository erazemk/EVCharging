package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import si.fri.prpo.skupina00.evcharging.entities.Station;
import si.fri.prpo.skupina00.evcharging.services.beans.StationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StationsResource {

    @Inject
    StationBean stationBean;

    @GET
    public Response getStations() {
        List<Station> stationList = stationBean.getStations();
        Response response;

        if (!stationList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(stationList).build();
        } else {
            // Internal error - can't retrieve stations from database
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @Path("{id}")
    public Response getStation(@PathParam("id") Integer id) {
        Station station = stationBean.getStation(id);
        Response response;

        if (station != null) {
            response = Response.status(Response.Status.OK).entity(station).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }
}
