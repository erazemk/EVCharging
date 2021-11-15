package si.fri.prpo.skupina00.evcharging.entities;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
