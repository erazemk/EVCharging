package si.fri.prpo.skupina00.evcharging.services.dtos;

public class ReservationDto {
    private Integer userId;
    private Integer stationId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
}
