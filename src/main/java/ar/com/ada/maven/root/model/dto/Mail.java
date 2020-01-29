package ar.com.ada.maven.root.model.dto;

import java.util.Objects;

public class Mail {
    private char mail;

    public Mail(char mail) {
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
        Mail mailDTO = (Mail) o;
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
