package ar.com.ada.maven.root.model.dto;

public class Account_type {
    private int id;
    private String name;
    private String code_control;

    public Account_type() { }

    public Account_type(int id, String name, String code_control) {
        this.id = id;
        this.name = name;
        this.code_control = code_control;
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

    public String getCode_control() {
        return code_control;
    }

    public void setCode_control(String code_control) {
        this.code_control = code_control;
    }

    @Override
    public String toString() {
        return "El id es: "+ id +
                ". El nombre es: " + name +
                ". El codigo de control es: " + code_control;
    }
}
