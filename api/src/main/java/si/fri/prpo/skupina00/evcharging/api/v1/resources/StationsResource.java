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
    private StationBean stationBean;

    @GET
    public Response getStations() {
        List<Station> stationList = stationBean.getStations();
        Response response;

        if (!stationList.isEmpty()) {
            response = Response.status(Response.Status.OK).entity(stationList).build();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GET
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

    @POST
    public Response addStation(Station station) {
        Response response;

        if (stationBean.addStation(station)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @PUT
    @Path("{id}")
    public Response updateStation(@PathParam("id") Integer id, Station station) {
        Response response;

        if (stationBean.updateStation(id, station)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteStation(@PathParam("id") Integer id) {
        Response response;

        if (stationBean.deleteStation(id)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }

        return response;
    }
}
