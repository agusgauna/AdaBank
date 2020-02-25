package ar.com.ada.maven.root.model.dto;

public class Branch {


    private Integer id;
    private String name;
    private String code;
    private Bank bank;

    public Branch() { }

    public Branch(Integer id, String name, String code, Bank bank) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.bank = bank;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El nombre de la sucursal es: " + name +
                ". El codigo de la sucursal es: " + code +
                ". El banco es: " + bank;
    }
}
