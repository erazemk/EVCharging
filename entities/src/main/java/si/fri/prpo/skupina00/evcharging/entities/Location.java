package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;

@javax.persistence.Entity(name = "locations")
@NamedQueries(value = {
        @NamedQuery(name = "Location.getAll", query = "SELECT l FROM locations l"),
        @NamedQuery(name = "Location.get", query = "SELECT l FROM locations l WHERE l.id = :id"),
        @NamedQuery(name = "Location.getCity", query = "SELECT l.city FROM locations l WHERE l.id = :id"),
        @NamedQuery(name = "Location.getAddress", query = "SELECT l.address FROM locations l WHERE l.id = :id"),
        @NamedQuery(name = "Location.getCoordinates", query = "SELECT l.xCoordinate, l.yCoordinate FROM locations l WHERE l.id = :id")
})
public class Location extends si.fri.prpo.skupina00.evcharging.entities.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    private String address;
    private Float xCoordinate;
    private Float yCoordinate;

    public Location() {
        super();
    }

    public Location(City city, String address, Float xCoordinate, Float yCoordinate) {
        super();
        this.city = city;
        this.address = address;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getXCoordinate() {
        return this.xCoordinate;
    }

    public void setXCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Float getYCoordinate() {
        return this.yCoordinate;
    }

    public void setYCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return this.id + "[" + this.city + ", " + this.address + " (" + this.xCoordinate + ", "
                + this.yCoordinate + ")]";
    }
}
