package si.fri.prpo.skupina00.evcharging.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "charges")
@NamedQueries(value = {
        @NamedQuery(name = "Charge.getAll", query = "SELECT c FROM Charge c"),
        @NamedQuery(name = "Charge.get", query = "SELECT c FROM Charge c WHERE c.id = :id"),
        @NamedQuery(name = "Charge.getPrice", query = "SELECT c.price FROM Charge c WHERE c.id = :id"),
        @NamedQuery(name = "Charge.getTime", query = "SELECT c.beginTime, c.endTime FROM Charge c WHERE c.id = :id")
})
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "stationId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station station;

    private Time beginTime;

    private Time endTime;

    private Float price;

    public Charge() {
        super();
    }

    public Charge(User user, Station station) {
        super();
        this.user = user;
        this.station = station;
        this.beginTime = Time.valueOf(LocalTime.now());
    }

    public Charge(User user, Station station, Time beginTime, Time endTime, Float price) {
        super();
        this.user = user;
        this.station = station;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", user=" + user +
                ", station=" + station +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", price=" + price +
                '}';
    }
}
