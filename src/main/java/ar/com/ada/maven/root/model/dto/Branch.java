package ar.com.ada.maven.root.model.dto;

public class Branch {


    private int id;
    private String name;
    private int code;
    private Bank bankId;

    public Branch() {
    }

    public Branch(int id, String name, int code, Bank bankId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.bankId = bankId;
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

    public Bank getBankId() {
        return bankId;
    }

    public void setBankId(Bank bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "Sucursal{" + "id=" + id + ", name='" + name + '\'' +
                ", code=" + code + ", bankId=" + bankId +
                '}';
    }
}
