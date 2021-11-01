package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;

@javax.persistence.Entity(name = "cities")
@NamedQueries(value = {
        @NamedQuery(name = "City.getAll", query = "SELECT c FROM cities c"),
        @NamedQuery(name = "City.get", query = "SELECT c FROM cities c WHERE c.id = :id"),
        @NamedQuery(name = "City.getId", query = "SELECT c.id FROM cities c WHERE c.name = :name"),
        @NamedQuery(name = "City.getName", query = "SELECT c.name FROM cities c WHERE c.id = :id")
})
public class City extends si.fri.prpo.skupina00.entitete.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public String getName() {
        return name;
    }

    public void setCityName(String name) {
        this.name = name;
    }
}
