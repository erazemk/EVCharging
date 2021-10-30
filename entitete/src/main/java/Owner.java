import java.util.ArrayList;

public class Owner extends Person {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private ArrayList<Station> ownedStations;

    public ArrayList<Station> getOwnedStations() {
        return ownedStations;
    }

    public void setOwnedStations(ArrayList<Station> ownedStations) {
        this.ownedStations = ownedStations;
    }
}
