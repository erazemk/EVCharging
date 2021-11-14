package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity(name = "reservations")
@NamedQueries(value = {
        @NamedQuery(name = "Reservation.getAll", query = "SELECT r FROM reservations r"),
        @NamedQuery(name = "Reservation.get", query = "SELECT r FROM reservations r WHERE r.user = :user AND r.station = :station AND r.reservationTime = :reservationTime"),
        @NamedQuery(name = "Reservation.getLocationCharges", query = "SELECT r FROM reservations r WHERE r.user = :user AND r.station = :station"),
        @NamedQuery(name = "Reservation.getUserCharges", query = "SELECT r FROM reservations r WHERE r.user = :user")
})
public class Reservation extends si.fri.prpo.skupina00.entitete.Entity {

    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    @Id
    private Time reservationTime;

    public Reservation() {
        // Za potrebe JPA
    }

    public Reservation(User user, Station station, Time reservationTime) {
        this.user = user;
        this.station = station;
        this.reservationTime = reservationTime;
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

    public String toString() {
        return user + ", " + station.getStationName() + ", " + reservationTime;
    }
}
