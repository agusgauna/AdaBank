package ar.com.ada.maven.root.model.dto;

public class Bank {

    private Integer id;
    private String name;
    private Integer code;
    private Country country;

    public Bank() { }

    public Bank(Integer id, String name, Integer code, Country country) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
                ". El codigo iban es: " + code +
                ". El pais del banco es: " + country;
    }
}
