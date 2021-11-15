package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.*;
import si.fri.prpo.skupina00.evcharging.services.dtos.ChargeDto;
import si.fri.prpo.skupina00.evcharging.services.dtos.CityDto;
import si.fri.prpo.skupina00.evcharging.services.dtos.ReservationDto;
import si.fri.prpo.skupina00.evcharging.services.dtos.StationDto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Logger;

@ApplicationScoped
public class ManageStationsBean {
    private static final Logger log = Logger.getLogger(ManageStationsBean.class.getName());

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
    private StationLocationBean stationLocationBean;

    @Inject
    private OwnerBean ownerBean;

    @PostConstruct
    private void init() {
        log.info("Initialized bean " + ManageStationsBean.class.getSimpleName());
        log.info(java.util.UUID.randomUUID().toString());
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroyed bean " + ManageStationsBean.class.getSimpleName());
    }

    @Transactional
    public boolean addCharge(ChargeDto chargeDto) {
        User user = userBean.getUser(chargeDto.getUserId());
        Station station = stationBean.getStation(chargeDto.getStationId());

        if (user == null) {
            log.warning("Could not add charge, user does not exist");
        }

        if (station == null) {
            log.warning("Could not add charge, station does not exist");
        }

        Charge charge = new Charge(user, station, Time.valueOf(LocalTime.now()));
        return chargeBean.addCharge(charge);
    }

    public boolean addReservation(ReservationDto reservationDto) {
        User user = userBean.getUser(reservationDto.getUserId());
        Station station = stationBean.getStation(reservationDto.getStationId());

        if (user == null) {
            log.warning("Could not add reservation, user does not exist");
        }

        if (station == null) {
            log.warning("Could not add reservation, station does not exist");
        }

        Reservation reservation = new Reservation(user, station, Time.valueOf(LocalTime.now()));
        return reservationBean.addReservation(reservation);
    }

    public boolean addStation(StationDto stationDto) {
        Owner owner = ownerBean.getOwner(stationDto.getOwnerId());
        StationLocation stationLocation = stationLocationBean.getStationLocation(stationDto.getStationLocationId());

        if (owner == null) {
            log.warning("Could not add station, owner does not exist");
        }

        if (stationLocation == null) {
            log.warning("Could not add station, station location does not exist");
        }

        Station station = new Station(stationDto.getStationName(), owner, stationDto.getOpenTime(),
                stationDto.getCloseTime(), stationDto.getPrice(), stationDto.getWattage(), stationDto.getAdapterType(),
                stationLocation);
        return stationBean.addStation(station);
    }

    public boolean addCity(CityDto cityDto) {
        City city = new City(cityDto.getName());
        return cityBean.addCity(city);
    }

}
