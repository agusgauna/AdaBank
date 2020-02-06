package ar.com.ada.maven.root.model.dto;

public class Country {
    public Integer id;
    public String name;
    public int code;
    private Country country;

    public Country(int id, String name, int code) {
    }

    public Country(Integer id, String name, int code, Country country) {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
        return "Country{" + "id=" + id + " name=" + name + '\'' + " code=" + code + " country=" + country + '}';
    }

}
