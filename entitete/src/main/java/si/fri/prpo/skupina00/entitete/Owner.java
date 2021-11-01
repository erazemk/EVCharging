package si.fri.prpo.skupina00.entitete;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity(name = "owners")
@NamedQueries(value = {
        @NamedQuery(name = "Owner.getAll", query = "SELECT o FROM owners o"),
        @NamedQuery(name = "Owner.allStations", query = "SELECT s.stationName FROM owners o, stations s WHERE o.id = s.ownerId AND o.email = :email"),
        @NamedQuery(name = "owners.deleteOwnerViaEmail", query = "DELETE FROM owners WHERE email=:email"),
        @NamedQuery(name = "owners.updateOwnerEmailViaEmail", query = "UPDATE owners SET email =:newEmail WHERE email=:oldEmail")
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
