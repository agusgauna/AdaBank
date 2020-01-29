package ar.com.ada.maven.root.model.dto;

public class Client {

    private int id;
    private String name;
    private String lastName;
    private int documentNumber;
    private String typeDocument;

    public Client (){

    }

    public Client(int id, String name, String lastName, int documentNumber, String typeDocument) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.typeDocument = typeDocument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id +
                ", name='" + name + '\'' + ", lastName='" + lastName + '\'' +
                ", documentNumber=" + documentNumber +
                ", typeDocument='" + typeDocument + '\'' +
                '}';
    }
}
