package ar.com.ada.maven.root.model.dto;

public class Bank {

    private Integer id;
    private String name;
    private Integer iban;
    private Country country;

    public Bank() { }

    public Bank(Integer id, String name, Integer iban, Country country) {
        this.id = id;
        this.name = name;
        this.iban = iban;
        this.country = country;
    }

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

    public Integer getIban() {
        return iban;
    }

    public void setIban(Integer iban) {
        this.iban = iban;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El nombre del banco es: " + name +
                ". El codigo iban es: " + iban +
                ". El pais del banco es: " + country;
    }
}
