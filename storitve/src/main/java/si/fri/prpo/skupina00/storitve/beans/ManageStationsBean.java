package si.fri.prpo.skupina00.storitve.beans;

import si.fri.prpo.skupina00.entitete.*;
import si.fri.prpo.skupina00.storitve.dto.ChargeDto;
import si.fri.prpo.skupina00.storitve.dto.CityDto;
import si.fri.prpo.skupina00.storitve.dto.ReservationDto;
import si.fri.prpo.skupina00.storitve.dto.StationDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Logger;

@ApplicationScoped
public class ManageStationsBean {
    private static final Logger log = Logger.getLogger(UserBean.class.getName());

    @Inject
    private UserBean userBean;

    @Inject
    private OwnerBean ownerBean;

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
