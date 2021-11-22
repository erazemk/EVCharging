package si.fri.prpo.skupina00.evcharging.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "owners")
@NamedQueries(value = {
        @NamedQuery(name = "Owner.getAll", query = "SELECT o FROM Owner o"),
        @NamedQuery(name = "Owner.get", query = "SELECT o FROM Owner o WHERE o.id = :id"),
        @NamedQuery(name = "Owner.getAllStations", query = "SELECT s FROM Station s WHERE s.owner = :owner"),
})

public class Owner extends si.fri.prpo.skupina00.evcharging.entities.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @JsonbTransient
    @OneToMany(mappedBy = "owner")
    private List<Station> ownedStations;

    public Owner() {
        super();
    }

    public Owner(String name, String surname, String email) {
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

    public List<Station> getOwnedStations() {
        return this.ownedStations;
    }

    public void addOwnedStation(Station station) {
        this.ownedStations.add(station);
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " <" + this.email + ">";
    }
}
