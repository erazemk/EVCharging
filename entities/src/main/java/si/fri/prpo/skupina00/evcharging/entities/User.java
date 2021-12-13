package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.get", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.getCharges", query = "SELECT c FROM Charge c WHERE c.user = :user"),
        @NamedQuery(name = "User.getReservations", query = "SELECT r FROM Reservation r WHERE r.user = :user"),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    public User() {
        super();
    }

    public User(String name, String surname, String email) {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
