package ar.com.ada.maven.root.model.dto;

public class Movement_type {
    private Integer id;
    private String debit;
    private String credit;

    public Movement_type() {}

    public Movement_type(Integer id, String debit, String credit) {
        this.id = id;
        this.debit = debit;
        this.credit = credit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }


    @Override
    public String toString() {
        return "El id es: " + id +
                ". El debito es de: " + debit +
                ". El credito es de: " + credit;
    }
}
