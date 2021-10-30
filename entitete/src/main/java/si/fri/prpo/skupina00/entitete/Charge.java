package si.fri.prpo.skupina00.entitete;

import java.sql.Time;

public class Charge extends si.fri.prpo.skupina00.entitete.Entity {
    private Integer userId;
    private Integer stationId;
    private Time beginTime;
    private Time endTime;
    private Float price;
}
