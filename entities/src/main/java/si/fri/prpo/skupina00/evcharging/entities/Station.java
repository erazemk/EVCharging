package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity(name = "stations")
@NamedQueries(value = {
        @NamedQuery(name = "Station.getAll", query = "SELECT s FROM stations s"),
        @NamedQuery(name = "Station.get", query = "SELECT s FROM stations s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getName", query = "SELECT s.stationName FROM stations s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getPrice", query = "SELECT s.price FROM stations s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getSchedule", query = "SELECT s.openTime, s.closeTime FROM stations s WHERE s.id = :id")
})
public class Station extends si.fri.prpo.skupina00.evcharging.entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String stationName;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    private Time openTime;

    private Time closeTime;

    private Float price;
    private Integer wattage;
    private String adapterType;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private StationLocation location;

    public Station() {
        // Za potrebe JPA
    }

    public Station(String stationName, Owner owner, Time openTime, Time closeTime, Float price, Integer wattage,
                   String adapterType, StationLocation stationLocation) {
        this.stationName = stationName;
        this.owner = owner;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.price = price;
        this.wattage = wattage;
        this.adapterType = adapterType;
        this.location = stationLocation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public void setAdapterType(String adapterType) {
        this.adapterType = adapterType;
    }

    public StationLocation getLocation() {
        return location;
    }

    public void setLocation(StationLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return stationName + "[" + owner + ", " + openTime + "-" + closeTime + ", " + price + "€, " + wattage + "W, " + adapterType + ", " + location;
    }
}
