package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity(name = "charge")
@NamedQueries(value = {
        @NamedQuery(name = "Charge.getAll", query = "SELECT c FROM charge c"),
        @NamedQuery(name = "Charge.get", query = "SELECT c FROM charge c WHERE c.id = :id"),
        @NamedQuery(name = "Charge.getUser", query = "SELECT c.userId FROM charge c WHERE c.id = :id"),
        @NamedQuery(name = "Charge.getStation", query = "SELECT c.stationId FROM charge c WHERE c.id = :id")
})
public class Charge extends si.fri.prpo.skupina00.entitete.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer stationId;
    private Time beginTime;
    private Time endTime;
    private Float price;
}
