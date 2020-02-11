package ar.com.ada.maven.root.model.dto;

public class Account_type {
    private Integer id;
    private String name;
    private Integer code_control;

    public Account_type() { }

    public Account_type(Integer id, String name, Integer code_control) {
        this.id = id;
        this.name = name;
        this.code_control = code_control;
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

    public Integer getCode_control() {
        return code_control;
    }

    public void setCode_control(Integer code_control) {
        this.code_control = code_control;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El nombre es: " + name +
                ". El codigo de control es: " + code_control;
    }
}
