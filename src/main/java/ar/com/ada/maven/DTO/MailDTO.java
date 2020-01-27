package ar.com.ada.maven.DTO;

import java.util.Objects;

public class MailDTO {
    private char mail;

    public MailDTO(char mail) {
        this.mail = mail;
    }

    public char getMail() {
        return mail;
    }

    public void setMail(char mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailDTO mailDTO = (MailDTO) o;
        return mail == mailDTO.mail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    @Override
    public String toString() {
        return "MailDTO{" + "mail=" + mail + '}';
    }
}
