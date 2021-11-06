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
}
