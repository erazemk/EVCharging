package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;

@javax.persistence.Entity(name = "stationLocations")
@NamedQueries(value = {
        @NamedQuery(name = "StationLocation.getAll", query = "SELECT l FROM stationLocations l"),
        @NamedQuery(name = "StationLocation.get", query = "SELECT l FROM stationLocations l WHERE l.id = :id"),
        @NamedQuery(name = "StationLocation.getCity", query = "SELECT l.city FROM stationLocations l WHERE l.id = :id"),
        @NamedQuery(name = "StationLocation.getAddress", query = "SELECT l.address FROM stationLocations l WHERE l.id = :id"),
        @NamedQuery(name = "StationLocation.getCoordinates", query = "SELECT l.xCoordinate, l.yCoordinate FROM stationLocations l WHERE l.id = :id")
})
public class StationLocation extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    private String address;
    private Float xCoordinate;
    private Float yCoordinate;

    public StationLocation() {
        // Za potrebe JPA
    }

    public StationLocation(City city, String address, Float xCoordinate, Float yCoordinate) {
        this.city = city;
        this.address = address;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Float getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return id + "[" + city + ", " + address + " (" + xCoordinate + ", " + yCoordinate + ")]";
    }
}
