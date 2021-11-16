package si.fri.prpo.skupina00.evcharging.services.dtos;

public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private List<ChargeDto> charges;
    private List<ReservationDto> reservations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(List<ChargeDto> charges) {
        this.charges = charges;
    }

    public Integer getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
