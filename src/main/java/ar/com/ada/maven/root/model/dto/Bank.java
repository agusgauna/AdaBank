package ar.com.ada.maven.root.model.dto;

public class Bank {

    private int id;
    private String name;
    private int iban;
    private Country countryId;

    public Bank() {
    }

    public Bank(int id, String name, int iban, Country countryId) {
        this.id = id;
        this.name = name;
        this.iban = iban;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIban() {
        return iban;
    }

    public void setIban(int iban) {
        this.iban = iban;
    }

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El nombre del banco es: " + name +
                ". El codigo iban es: " + iban +
                ". El pais del banco es: " + countryId;
    }
}
