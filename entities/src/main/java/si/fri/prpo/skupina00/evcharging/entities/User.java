package si.fri.prpo.skupina00.evcharging.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.get", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.getCharges", query = "SELECT c FROM Charge c WHERE c.user = :user"),
        @NamedQuery(name = "User.getReservations", query = "SELECT r FROM Reservation r WHERE r.user = :user"),
})
public class User extends si.fri.prpo.skupina00.evcharging.entities.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Charge> charges;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservations;

    public User() {
        super();
    }

    public User(String name, String surname, String email) {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Charge> getCharges() {
        return this.charges;
    }

    public void addCharge(Charge charge) {
        this.charges.add(charge);
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " <" + this.email + ">";
    }
}
