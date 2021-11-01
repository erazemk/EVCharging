package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;

@javax.persistence.Entity(name = "stationLocation")
@NamedQueries(value = {
        @NamedQuery(name = "StationLocation.getAll", query = "SELECT l FROM stationLocation l"),
        @NamedQuery(name = "StationLocation.getCity", query = "SELECT l.city FROM stationLocation l"),
        @NamedQuery(name = "StationLocation.getAddress", query = "SELECT l.address FROM stationLocation l"),
        @NamedQuery(name = "StationLocation.getCoordinates", query = "SELECT l.xCoordinate, l.yCoordinate FROM stationLocation l")
})
public class StationLocation extends si.fri.prpo.skupina00.entitete.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    private String address;
    private Float xCoordinate;
    private Float yCoordinate;

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

    public Float getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Float getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
