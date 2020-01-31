package ar.com.ada.maven.root.model.dto;

import java.util.Objects;

public class Movement_type {
    private Integer id;
    private String debit;
    private String credit;
    private Account account;

    public Movement_type() {}

    public Movement_type(Integer id, String debit, String credit, Account account) {
        this.id = id;
        this.debit = debit;
        this.credit = credit;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". El debito es de: " + debit +
                ". El credito es de: " + credit +
                ". La cuenta es: " + account;
    }
}
