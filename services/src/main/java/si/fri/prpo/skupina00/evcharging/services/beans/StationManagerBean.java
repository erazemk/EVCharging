package si.fri.prpo.skupina00.evcharging.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina00.evcharging.entities.*;
import si.fri.prpo.skupina00.evcharging.services.annotations.LogCalls;
import si.fri.prpo.skupina00.evcharging.services.dtos.*;
import si.fri.prpo.skupina00.evcharging.services.exceptions.InvalidRequestException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@LogCalls
public class StationManagerBean {
    private static final Logger log = Logger.getLogger(StationManagerBean.class.getName());

    @Inject
    private Mapper mapper;

    @Inject
    private UserBean userBean;

    @Inject
    private CityBean cityBean;

    @Inject
    private ChargeBean chargeBean;

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private StationBean stationBean;

    @Inject
    private LocationBean locationBean;

    @Inject
    private OwnerBean ownerBean;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + StationManagerBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + StationManagerBean.class.getSimpleName());
    }

    public List<ChargeDto> getCharges(QueryParameters queryParameters) {
        List<Charge> charges = chargeBean.getCharges(queryParameters);
        List<ChargeDto> chargeDtos = new ArrayList<>(charges.size());

        for (Charge charge : charges) {
            chargeDtos.add(mapper.mapCharge(charge));
        }

        return chargeDtos;
    }

    public ChargeDto getCharge(Integer id) {
        Charge charge = chargeBean.getCharge(id);

        if (charge != null) {
            return mapper.mapCharge(charge);
        }

        throw new InvalidRequestException("Could not find charge");
    }

    @Transactional
    public ChargeDto addCharge(ChargeDto chargeDto) {
        Charge charge = null;

        if (chargeDto != null) {
            charge = chargeBean.addCharge(mapper.mapCharge(chargeDto));
        }

        if (charge != null) {
            return mapper.mapCharge(charge);
        }

        throw new InvalidRequestException("Could not add charge");
    }

    @Transactional
    public ChargeDto updateCharge(Integer id, ChargeDto chargeDto) {
        Charge charge = null;

        if (chargeDto != null) {
            charge = chargeBean.updateCharge(id, mapper.mapCharge(chargeDto));
        }

        if (charge != null) {
            return mapper.mapCharge(charge);
        }

        throw new InvalidRequestException("Could not update charge");
    }

    @Transactional
    public boolean deleteCharge(Integer id) {
        if (chargeBean.deleteCharge(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete charge");
    }

    public List<CityDto> getCities(QueryParameters queryParameters) {
        List<City> cities = cityBean.getCities(queryParameters);
        List<CityDto> cityDtos = new ArrayList<>(cities.size());

        for (City city : cities) {
            cityDtos.add(mapper.mapCity(city));
        }

        return cityDtos;
    }

    public CityDto getCity(Integer id) {
        City city = cityBean.getCity(id);

        if (city != null) {
            return mapper.mapCity(city);
        }

        throw new InvalidRequestException("Could not find city");
    }

    @Transactional
    public CityDto addCity(CityDto cityDto) {
        City city = null;

        if (cityDto != null) {
            city = cityBean.addCity(mapper.mapCity(cityDto));
        }

        if (city != null) {
            return mapper.mapCity(city);
        }

        throw new InvalidRequestException("Could not add city");
    }

    @Transactional
    public CityDto updateCity(Integer id, CityDto cityDto) {
        City city = null;

        if (cityDto != null) {
            city = cityBean.updateCity(id, mapper.mapCity(cityDto));
        }

        if (city != null) {
            return mapper.mapCity(city);
        }

        throw new InvalidRequestException("Could not update city");
    }

    @Transactional
    public boolean deleteCity(Integer id) {
        if (cityBean.deleteCity(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete city");
    }

    public List<LocationDto> getLocations(QueryParameters queryParameters) {
        List<Location> locations = locationBean.getLocations(queryParameters);
        List<LocationDto> locationDtos = new ArrayList<>(locations.size());

        for (Location location : locations) {
            locationDtos.add(mapper.mapLocation(location));
        }

        return locationDtos;
    }

    public LocationDto getLocation(Integer id) {
        Location location = locationBean.getLocation(id);

        if (location != null) {
            return mapper.mapLocation(location);
        }

        throw new InvalidRequestException("Could not find location");
    }

    @Transactional
    public LocationDto addLocation(LocationDto locationDto) {
        Location location = null;

        if (locationDto != null) {
            location = locationBean.addLocation(mapper.mapLocation(locationDto));
        }

        if (location != null) {
            return mapper.mapLocation(location);
        }

        throw new InvalidRequestException("Could not add location");
    }

    @Transactional
    public LocationDto updateLocation(Integer id, LocationDto locationDto) {
        Location location = null;

        if (locationDto != null) {
            location = locationBean.updateLocation(id, mapper.mapLocation(locationDto));
        }

        if (location != null) {
            return mapper.mapLocation(location);
        }

        throw new InvalidRequestException("Could not update location");
    }

    @Transactional
    public boolean deleteLocation(Integer id) {
        if (locationBean.deleteLocation(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete location");
    }

    public List<ReservationDto> getReservations(QueryParameters queryParameters) {
        List<Reservation> reservations = reservationBean.getReservations(queryParameters);
        List<ReservationDto> reservationDtos = new ArrayList<>(reservations.size());

        for (Reservation reservation : reservations) {
            reservationDtos.add(mapper.mapReservation(reservation));
        }

        return reservationDtos;
    }

    public ReservationDto getReservation(Integer id) {
        Reservation reservation = reservationBean.getReservation(id);

        if (reservation != null) {
            return mapper.mapReservation(reservation);
        }

        throw new InvalidRequestException("Could not find reservation");
    }

    @Transactional
    public ReservationDto addReservation(ReservationDto reservationDto) {
        Reservation reservation = null;

        if (reservationDto != null) {
            reservation = reservationBean.addReservation(mapper.mapReservation(reservationDto));
        }

        if (reservation != null) {
            return mapper.mapReservation(reservation);
        }

        throw new InvalidRequestException("Could not add reservation");
    }

    @Transactional
    public ReservationDto updateReservation(Integer id, ReservationDto reservationDto) {
        Reservation reservation = null;

        if (reservationDto != null) {
            reservation = reservationBean.updateReservation(id, mapper.mapReservation(reservationDto));
        }

        if (reservation != null) {
            return mapper.mapReservation(reservation);
        }

        throw new InvalidRequestException("Could not update reservation");
    }

    @Transactional
    public boolean deleteReservation(Integer id) {
        if (reservationBean.deleteReservation(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete reservation");
    }

    public List<StationDto> getStations(QueryParameters queryParameters) {
        List<Station> stations = stationBean.getStations(queryParameters);
        List<StationDto> stationDtos = new ArrayList<>(stations.size());

        for (Station station : stations) {
            stationDtos.add(mapper.mapStation(station));
        }

        return stationDtos;
    }

    public StationDto getStation(Integer id) {
        Station station = stationBean.getStation(id);

        if (station != null) {
            return mapper.mapStation(station);
        }

        throw new InvalidRequestException("Could not find station");
    }

    @Transactional
    public StationDto addStation(StationDto stationDto) {
        Station station = null;

        if (stationDto != null) {
            station = stationBean.addStation(mapper.mapStation(stationDto));
        }

        if (station != null) {
            return mapper.mapStation(station);
        }

        throw new InvalidRequestException("Could not add station");
    }

    @Transactional
    public StationDto updateStation(Integer id, StationDto stationDto) {
        Station station = null;

        if (stationDto != null) {
            station = stationBean.updateStation(id, mapper.mapStation(stationDto));
        }

        if (station != null) {
            return mapper.mapStation(station);
        }

        throw new InvalidRequestException("Could not update station");
    }

    @Transactional
    public boolean deleteStation(Integer id) {
        if (stationBean.deleteStation(id)) {
            return true;
        }

        throw new InvalidRequestException("Could not delete station");
    }
}
