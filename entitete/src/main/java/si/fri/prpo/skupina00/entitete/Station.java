package si.fri.prpo.skupina00.entitete;

import java.sql.Time;

public class Station extends si.fri.prpo.skupina00.entitete.Entity {
    private Integer id;
    private String stationName;
    private Time openTime;
    private Time closeTime;
    private Float price;
    private Integer wattage;
    private String adapterType;
    private StationLocation location;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public void setAdapterType(String adapterType) {
        this.adapterType = adapterType;
    }

    public StationLocation getLocation() {
        return location;
    }

    public void setLocation(StationLocation location) {
        this.location = location;
    }
}
