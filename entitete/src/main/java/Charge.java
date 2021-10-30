import java.sql.Time;

public class Charge extends Entity {
    private Integer userId;
    private Integer stationId;
    private Time beginTime;
    private Time endTime;
    private Float price;
}
