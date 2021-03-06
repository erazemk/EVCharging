package si.fri.prpo.skupina00.evcharging.entities;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@NamedQueries(value = {
        @NamedQuery(name = "City.getAll", query = "SELECT c FROM City c"),
        @NamedQuery(name = "City.get", query = "SELECT c FROM City c WHERE c.id = :id"),
        @NamedQuery(name = "City.getId", query = "SELECT c.id FROM City c WHERE c.name = :name"),
        @NamedQuery(name = "City.getName", query = "SELECT c.name FROM City c WHERE c.id = :id")
})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public City() {
        super();
    }

    public City(String name) {
        super();
        this.name = name;
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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
