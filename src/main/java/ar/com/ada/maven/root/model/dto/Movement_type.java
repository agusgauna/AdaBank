package ar.com.ada.maven.root.model.dto;

import java.util.Objects;

public class Movement_type {
    private char debit;
    private char credit;

    public Movement_type(char debit, char credit) {
        this.debit = debit;
        this.credit = credit;
    }

    public char getDebit() {
        return debit;
    }

    public char getCredit() {
        return credit;
    }

    public void setDebit(char debit) {
        this.debit = debit;
    }

    public void setCredit(char credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement_type that = (Movement_type) o;
        return debit == that.debit &&
                credit == that.credit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(debit, credit);
    }

    @Override
    public String toString() {
        return "Movement_typeDTO{" + "debit=" + debit + ", credit=" + credit + '}';
    }
}
