package si.fri.prpo.skupina00.evcharging.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@NamedQueries(value = {
        @NamedQuery(name = "Location.getAll", query = "SELECT l FROM Location l"),
        @NamedQuery(name = "Location.get", query = "SELECT l FROM Location l WHERE l.id = :id"),
        @NamedQuery(name = "Location.getCity", query = "SELECT l.city FROM Location l WHERE l.id = :id"),
        @NamedQuery(name = "Location.getAddress", query = "SELECT l.address FROM Location l WHERE l.id = :id"),
        @NamedQuery(name = "Location.getCoordinates", query = "SELECT l.xCoordinate, l.yCoordinate FROM Location l WHERE l.id = :id")
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cityId")
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Location{" +
                "id=" + id +
                ", city=" + city +
                ", address='" + address + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
