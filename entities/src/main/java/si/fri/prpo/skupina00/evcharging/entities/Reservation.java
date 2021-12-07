package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@NamedQueries(value = {
        @NamedQuery(name = "Reservation.getAll", query = "SELECT r FROM Reservation r"),
        @NamedQuery(name = "Reservation.get", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
        @NamedQuery(name = "Reservation.getLocationCharges", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
        @NamedQuery(name = "Reservation.getUserCharges", query = "SELECT r FROM Reservation r WHERE r.user = :user")
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    private Time reservationTime;

    public Reservation() {
        super();
    }

    public Reservation(User user, Station station) {
        super();
        this.user = user;
        this.station = station;
        this.reservationTime = Time.valueOf(LocalTime.now());
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

    public Time getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Time reservationTime) {
        this.reservationTime = reservationTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", station=" + station +
                ", reservationTime=" + reservationTime +
                '}';
    }
}
