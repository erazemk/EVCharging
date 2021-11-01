package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import java.sql.Time;

@javax.persistence.Entity(name = "station")
@NamedQueries(value = {
        @NamedQuery(name = "Station.getAll", query = "SELECT s FROM station s"),
        @NamedQuery(name = "Station.getStation", query = "SELECT s FROM station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getName", query = "SELECT s.stationName FROM station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getPrice", query = "SELECT s.price FROM station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.getSchedule", query = "SELECT s.openTime, s.closeTime FROM station s WHERE s.id = :id"),
        @NamedQuery(name = "Station.delete", query = "DELETE FROM station WHERE id = :id"),
        @NamedQuery(name = "Station.updatePrice", query = "UPDATE station SET price = :price WHERE id = :id")
})
public class Station extends si.fri.prpo.skupina00.entitete.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String stationName;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
