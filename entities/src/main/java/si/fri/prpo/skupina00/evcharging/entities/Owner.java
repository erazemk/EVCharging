package si.fri.prpo.skupina00.evcharging.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
@NamedQueries(value = {
        @NamedQuery(name = "Owner.getAll", query = "SELECT o FROM Owner o"),
        @NamedQuery(name = "Owner.get", query = "SELECT o FROM Owner o WHERE o.id = :id"),
        @NamedQuery(name = "Owner.getAllStations", query = "SELECT s FROM Station s WHERE s.owner = :owner"),
})

public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
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

    public List<Station> getOwnedStations() {
        return ownedStations;
    }

    public void setOwnedStations(List<Station> ownedStations) {
        this.ownedStations = ownedStations;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", ownedStations=" + ownedStations +
                '}';
    }
}
