package ar.com.ada.maven.root.model.dto;

import java.util.Date;
import java.util.Objects;

public class Movement {
    private Date date;
    private double amount;
    private char description;

    public Movement(Date date, double amount, Date date1, char description) {
        this.date = date;
        this.amount = amount;
        this.date = date1;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getDescription() {
        return description;
    }

    public void setDescription(char description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement that = (Movement) o;
        return Double.compare(that.amount, amount) == 0 &&
                description == that.description &&
                Objects.equals(date, that.date) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, date, description);
    }

    @Override
    public String toString() {
        return "MovementDTO{" + "date=" + date + ", amount=" + amount + ", date=" + date + ", description=" + description + '}';
    }
}
