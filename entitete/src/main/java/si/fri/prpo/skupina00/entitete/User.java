package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM users u"),
        @NamedQuery(name = "User.getAllCharges", query = "SELECT c FROM charges c, users u WHERE u.id = c.userId AND u.id = :id"),
        @NamedQuery(name = "User.getAllReservations", query = "SELECT r FROM reservations r, users u WHERE r.userId=u.id AND u.id = :id"),
        @NamedQuery(name = "User.updateEmailViaEmail", query = "UPDATE users SET email = :newEmail WHERE email = :oldEmail")
})
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @OneToMany
    @JoinColumn(name = "userId")
    private ArrayList<Charge> charges;

    @OneToMany
    @JoinColumn(name = "userId")
    private ArrayList<Reservation> reservations;

    public ArrayList<Charge> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<Charge> charges) {
        this.charges = charges;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
