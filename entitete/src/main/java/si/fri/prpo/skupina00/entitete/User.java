package si.fri.prpo.skupina00.entitete;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM users u"),
        @NamedQuery(name = "User.get", query = "SELECT u FROM users u WHERE u.email = :email"),
        @NamedQuery(name = "User.getCharges", query = "SELECT c FROM charges c WHERE c.user.email = :email"),
        @NamedQuery(name = "User.getReservations", query = "SELECT r FROM reservations r WHERE r.user.email = :email"),
})
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Charge> charges;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public User() {
        // Za potrebe JPA
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void addCharge(Charge charge) {
        this.charges.add(charge);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public String toString() {
        return name + " " + surname + " <" + email + ">";
    }
}
