package si.fri.prpo.skupina00.entitete;

public class Person extends si.fri.prpo.skupina00.entitete.Entity {
    private Integer id;
    private String name;
    private String surname;
    private String email;

    public Person() {
        // Za potrebe JPA ?
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    @Override
    public String toString() {
        return name + " " + surname + " <" + email + ">";
    }
}
