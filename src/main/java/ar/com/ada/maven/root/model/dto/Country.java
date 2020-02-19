package ar.com.ada.maven.root.model.dto;

public class Country {
    private Integer id;
    private String name;
    private String code;

    public Country() { }

    public Country(Integer id, String name, String code) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El pais es: " + name + '\'' +
                ". El codigo del pais es: " + code;
    }
}
