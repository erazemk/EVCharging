package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity(name = "owners")
@NamedQueries(value = {
        @NamedQuery(name = "Owner.getAll", query = "SELECT o FROM owners o"),
        @NamedQuery(name = "Owner.get", query = "SELECT o FROM owners o WHERE o.id = :id"),
        @NamedQuery(name = "Owner.getAllStations", query = "SELECT s FROM stations s WHERE s.owner = :owner"),
})

public class Owner extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Station> ownedStations;

    public Owner() {
        // Za potrebe JPA
    }

    public Owner(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    public List<Station> getOwnedStations() {
        return ownedStations;
    }

    public void addOwnedStation(Station station) {
        this.ownedStations.add(station);
    }

    @Override
    public String toString() {
        return name + " " + surname + " <" + email + ">";
    }
}
