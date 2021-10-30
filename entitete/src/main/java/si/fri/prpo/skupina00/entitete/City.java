package si.fri.prpo.skupina00.entitete;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;

@Entity
public class City extends si.fri.prpo.skupina00.entitete.Entity {
    private Integer id;
    private String name;

    public String getName() {
        return name;
    }

    public void setCityName(String name) {
        this.name = name;
    }
}
