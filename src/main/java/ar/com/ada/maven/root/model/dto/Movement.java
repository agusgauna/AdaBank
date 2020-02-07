package ar.com.ada.maven.root.model.dto;

import java.util.Date;

public class Movement {
    private Integer id;
    private Date date;
    private Double amount;
    private String description;
    private Account account;
    private Movement_type movement_type;

    public Movement() { }

    public Movement(Integer id, Date date, Double amount, String description, Account account, Movement_type movement_type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.movement_type = movement_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Movement_type getMovement_type() {
        return movement_type;
    }

    public void setMovement_type(Movement_type movement_type) {
        this.movement_type = movement_type;
    }

    @Override
    public String toString() {
        return "El id es: " + id +
                ". La fecha es: " + date +
                ". El saldo es: " + amount +
                ". La descripcion es: " + description +
                ". La cuenta es: " + account +
                ". El tipo de cuenta: " + movement_type;
    }
}
