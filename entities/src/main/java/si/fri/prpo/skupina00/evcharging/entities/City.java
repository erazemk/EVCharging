package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;

@javax.persistence.Entity(name = "cities")
@NamedQueries(value = {
        @NamedQuery(name = "City.getAll", query = "SELECT c FROM cities c"),
        @NamedQuery(name = "City.get", query = "SELECT c FROM cities c WHERE c.id = :id"),
        @NamedQuery(name = "City.getId", query = "SELECT c.id FROM cities c WHERE c.name = :name"),
        @NamedQuery(name = "City.getName", query = "SELECT c.name FROM cities c WHERE c.id = :id")
})
public class City extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public City() {
        // Za potrebe JPA
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
