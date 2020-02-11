package ar.com.ada.maven.root.model.dto;

public class Country {
    public Integer id;
    public String name;
    public Integer code;

    public Country() { }

    public Country(Integer id, String name, Integer code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El pais es: " + name + '\'' +
                ". El codigo del pais es: " + code;
    }
}
