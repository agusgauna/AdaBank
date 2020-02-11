package ar.com.ada.maven.root.model.dto;

public class Branch {


    private Integer id;
    private String name;
    private Integer code;
    private Bank bankId;

    public Branch() { }

    public Branch(Integer id, String name, Integer code, Bank bankId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.bankId = bankId;
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

    public Bank getBankId() {
        return bankId;
    }

    public void setBankId(Bank bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El nombre de la sucursal es: " + name +
                ". El codigo de la sucursal es: " + code +
                ". El banco es: " + bankId;
    }
}
