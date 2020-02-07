package ar.com.ada.maven.root.model.dto;

import java.util.Objects;

public class Contact {
    public Integer id;
    public String mail;
    public int telephone;
    private Client client;

    public Contact(){}

    public Contact(Integer id, String mail, int telephone, Client client){
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
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
        return "Contact{" + "id=" + id + " mail='" + mail + '\'' + " telephone=" + telephone + " client=" + client + '}';
    }
}
