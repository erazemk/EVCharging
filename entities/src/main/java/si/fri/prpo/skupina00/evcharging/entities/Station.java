package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity
@Table(name = "stations")
@NamedQueries(value = {
        @NamedQuery(name = "Station.getAll", query = "SELECT s FROM Station s"),
        @NamedQuery(name = "Station.get", query = "SELECT s FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getName", query = "SELECT s.name FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getPrice", query = "SELECT s.price FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getSchedule", query = "SELECT s.openTime, s.closeTime FROM Station s WHERE s.id = :id")
})
public class Station extends si.fri.prpo.skupina00.evcharging.entities.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

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
    private Location location;

    public Station() {
        super();
    }

    public Station(String name, Owner owner, Time openTime, Time closeTime, Float price, Integer wattage,
                   String adapterType, Location location) {
        super();
        this.name = name;
        this.owner = owner;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.price = price;
        this.wattage = wattage;
        this.adapterType = adapterType;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Time getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getWattage() {
        return this.wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getAdapterType() {
        return this.adapterType;
    }

    public void setAdapterType(String adapterType) {
        this.adapterType = adapterType;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return this.name + "[" + this.owner + ", " + this.openTime + "-" + this.closeTime
                + ", " + this.price + "€, " + this.wattage + "W, " + this.adapterType + ", " + this.location;
    }
}
