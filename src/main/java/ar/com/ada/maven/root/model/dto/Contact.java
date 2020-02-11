package ar.com.ada.maven.root.model.dto;

import java.util.Objects;

public class Contact {
    public Integer id;
    public String mail;
    public Integer telephone;
    private Client client;

    public Contact() { }

    public Contact(Integer id, String mail, Integer telephone, Client client) {
        this.id = id;
        this.mail = mail;
        this.telephone = telephone;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "El id del contacto es: " + id +
                ". El mail de contacto es: " + mail +
                ". El telefono de contacto es: " + telephone +
                ". El cliente es: " + client;
    }
}
