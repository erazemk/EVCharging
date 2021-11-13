package si.fri.prpo.skupina00.storitve.dto;

public class StationLocationDto {
    private Integer id;
    private Integer cityId;
    private String address;
    private Float xCoordinate;
    private Float yCoordinate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Float getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
