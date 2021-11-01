package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity(name = "reservation")
@NamedQueries(value = {
        @NamedQuery(name = "Reservation.getAll", query = "SELECT r FROM reservation r"),
        @NamedQuery(name = "Reservation.get", query = "SELECT r FROM reservation r WHERE r.userId = :uId AND r.stationId = :sId AND r.reservationTime = :rId"),
        @NamedQuery(name = "Reservation.getLocationCharges", query = "SELECT r FROM reservation r WHERE r.userId = :uId AND r.stationId = :sId"),
        @NamedQuery(name = "Reservation.getUserCharges", query = "SELECT r FROM reservation r WHERE r.userId = :uId")
})

public class Reservation extends si.fri.prpo.skupina00.entitete.Entity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stationId")
    private Station station;

    @Id
    private Time reservationTime;

    public void createReservation(Integer userId, Integer stationId){

    }
}
