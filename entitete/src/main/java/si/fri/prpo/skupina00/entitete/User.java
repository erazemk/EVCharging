package si.fri.prpo.skupina00.entitete;

import java.util.ArrayList;

public class User extends Person {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private ArrayList<Charge> charges;
    private ArrayList<Reservation> reservations;

    public ArrayList<Charge> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<Charge> charges) {
        this.charges = charges;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
