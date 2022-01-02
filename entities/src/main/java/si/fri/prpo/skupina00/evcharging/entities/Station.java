package si.fri.prpo.skupina00.evcharging.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "stations")
@NamedQueries(value = {
        @NamedQuery(name = "Station.getAll", query = "SELECT s FROM Station s"),
        @NamedQuery(name = "Station.get", query = "SELECT s FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getName", query = "SELECT s.name FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getPrice", query = "SELECT s.price FROM Station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getSchedule", query = "SELECT s.openTime, s.closeTime FROM Station s WHERE s.id = :id")
})
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ownerId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Owner owner;

    private Time openTime;

    private Time closeTime;

    private Float price;
    private Integer wattage;
    private String adapterType;

    @ManyToOne
    @JoinColumn(name = "locationId")
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", price=" + price +
                ", wattage=" + wattage +
                ", adapterType='" + adapterType + '\'' +
                ", location=" + location +
                '}';
    }
}
