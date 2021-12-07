package si.fri.prpo.skupina00.evcharging.services.beans;

import si.fri.prpo.skupina00.evcharging.entities.*;
import si.fri.prpo.skupina00.evcharging.services.dtos.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Mapper {
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
    private LocationBean locationBean;

    public Charge mapCharge(ChargeDto chargeDto) {
        Charge charge = new Charge();
        User user = userBean.getUser(chargeDto.getUserId());
        Station station = stationBean.getStation(chargeDto.getStationId());

        charge.setId(chargeDto.getId());
        charge.setUser(user);
        charge.setStation(station);

        return charge;
    }

    public ChargeDto mapCharge(Charge charge) {
        ChargeDto chargeDto = new ChargeDto();

        chargeDto.setId(charge.getId());
        chargeDto.setStationId(charge.getStation().getId());
        chargeDto.setUserId(charge.getUser().getId());

        return chargeDto;
    }

    public City mapCity(CityDto cityDto) {
        City city = new City();

        city.setId(cityDto.getId());
        city.setName(cityDto.getName());

        return city;
    }

    public CityDto mapCity(City city) {
        CityDto cityDto = new CityDto();

        cityDto.setId(city.getId());
        cityDto.setName(city.getName());

        return cityDto;
    }

    public Location mapLocation(LocationDto locationDto) {
        Location location = new Location();
        City city = cityBean.getCity(location.getCity().getId());

        location.setId(locationDto.getId());
        location.setCity(city);
        location.setAddress(locationDto.getAddress());
        location.setXCoordinate(locationDto.getXCoordinate());
        location.setYCoordinate(locationDto.getYCoordinate());

        return location;
    }

    public LocationDto mapLocation(Location location) {
        LocationDto locationDto = new LocationDto();

        locationDto.setId(location.getId());
        locationDto.setCityId(location.getCity().getId());
        locationDto.setAddress(location.getAddress());
        locationDto.setXCoordinate(location.getXCoordinate());
        locationDto.setYCoordinate(location.getYCoordinate());

        return locationDto;
    }

    public Owner mapOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();

        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setSurname(ownerDto.getSurname());
        owner.setEmail(ownerDto.getEmail());

        return owner;
    }

    public OwnerDto mapOwner(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setSurname(owner.getSurname());
        ownerDto.setEmail(owner.getEmail());

        return ownerDto;
    }

    public Reservation mapReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        User user = userBean.getUser(reservationDto.getUserId());
        Station station = stationBean.getStation(reservationDto.getStationId());

        reservation.setId(reservationDto.getId());
        reservation.setUser(user);
        reservation.setStation(station);

        return reservation;
    }

    public ReservationDto mapReservation(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(reservation.getId());
        reservationDto.setStationId(reservation.getStation().getId());
        reservationDto.setUserId(reservation.getUser().getId());

        return reservationDto;
    }

    public Station mapStation(StationDto stationDto) {
        Station station = new Station();
        Owner owner = ownerBean.getOwner(stationDto.getOwnerId());
        Location location = locationBean.getLocation(stationDto.getLocationId());

        station.setId(stationDto.getId());
        station.setName(stationDto.getName());
        station.setOwner(owner);
        station.setOpenTime(stationDto.getOpenTime());
        station.setCloseTime(stationDto.getCloseTime());
        station.setPrice(stationDto.getPrice());
        station.setWattage(stationDto.getWattage());
        station.setAdapterType(stationDto.getAdapterType());
        station.setLocation(location);

        return station;
    }

    public StationDto mapStation(Station station) {
        StationDto stationDto = new StationDto();

        stationDto.setId(station.getId());
        stationDto.setName(stationDto.getName());
        stationDto.setOwnerId(station.getOwner().getId());
        stationDto.setOpenTime(station.getOpenTime());
        stationDto.setCloseTime(station.getCloseTime());
        stationDto.setPrice(station.getPrice());
        stationDto.setWattage(station.getWattage());
        stationDto.setAdapterType(station.getAdapterType());
        stationDto.setLocationId(station.getLocation().getId());

        return stationDto;
    }

    public User mapUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public UserDto mapUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
