package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity(name = "charges")
@NamedQueries(value = {
        @NamedQuery(name = "Charge.getAll", query = "SELECT c FROM charges c"),
        @NamedQuery(name = "Charge.get", query = "SELECT c FROM charges c WHERE c.user = :user AND c.station = :station AND c.beginTime = :beginTime"),
        @NamedQuery(name = "Charge.getPrice", query = "SELECT c.price FROM charges c WHERE c.user = :user AND c.station = :station AND c.beginTime = :beginTime"),
        @NamedQuery(name = "Charge.getTime", query = "SELECT c.beginTime, c.endTime FROM charges c WHERE c.user = :user AND c.station = :station AND c.beginTime = :beginTime")
})
public class Charge extends si.fri.prpo.skupina00.entitete.Entity {

    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    @Id
    private Time beginTime;

    private Time endTime;

    private Float price;

    public Charge() {
        // Za potrebe JPA
    }

    public Charge(User user, Station station, Time beginTime) {
        this.user = user;
        this.station = station;
        this.beginTime = beginTime;
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
}
