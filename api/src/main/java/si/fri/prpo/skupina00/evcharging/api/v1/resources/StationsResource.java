package si.fri.prpo.skupina00.evcharging.api.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.Station;
import si.fri.prpo.skupina00.evcharging.services.beans.StationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StationsResource {

    @Inject
    private StationBean stationBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getStations() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        return Response
                .status(Response.Status.OK)
                .entity(stationBean.getStations(queryParameters))
                .header("X-Total-Count", stationBean.getStationCount(queryParameters))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getStation(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(stationBean.getStation(id))
                .build();
    }

    @POST
    public Response addStation(Station station) {
        if (stationBean.addStation(station)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateStation(@PathParam("id") Integer id, Station station) {
        if (stationBean.updateStation(id, station)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStation(@PathParam("id") Integer id) {
        if (stationBean.deleteStation(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
