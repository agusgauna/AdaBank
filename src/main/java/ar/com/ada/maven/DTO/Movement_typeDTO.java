package ar.com.ada.maven.DTO;

import java.util.Objects;

public class Movement_typeDTO {
    private char debit;
    private char credit;

    public Movement_typeDTO(char debit, char credit) {
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
        Movement_typeDTO that = (Movement_typeDTO) o;
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
