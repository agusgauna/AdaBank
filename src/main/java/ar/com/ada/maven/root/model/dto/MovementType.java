package ar.com.ada.maven.root.model.dto;

public class MovementType {
    private Integer id;
    private String type;

    public MovementType() {
    }

    public MovementType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "El id es: " + id +
                ". El debito es de: " + type;
    }
}
