package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity(name = "owner")
@NamedQueries(value = {
        @NamedQuery(name = "Owner.getAll", query = "SELECT o FROM owner o"),
        @NamedQuery(name = "Owner.allStations", query = "SELECT s.stationName FROM owner o, station s WHERE o.email = :email"),
        @NamedQuery(name = "owner.deleteOwnerViaEmail", query = "DELETE FROM owner WHERE email=:email"),
        @NamedQuery(name = "owner.updateOwnerEmailViaEmail", query = "UPDATE owner SET email =:newEmail WHERE email=:oldEmail")
})

public class Owner extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;

    @OneToMany
    @JoinColumn(name = "ownerId")
    private ArrayList<Station> ownedStations;

    public ArrayList<Station> getOwnedStations() {
        return ownedStations;
    }

    public void setOwnedStations(ArrayList<Station> ownedStations) {
        this.ownedStations = ownedStations;
    }
}
