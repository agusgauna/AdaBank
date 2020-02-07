package ar.com.ada.maven.root.model.dto;

public class Country {
    public int id;
    public String name;
    public int code;

    public Country() { }

    public Country(int id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El pais es: " + name + '\'' +
                ". El codigo del pais es: " + code;
    }
}
