package ar.com.ada.maven.root.model.dto;

public class Client {

    private int id;
    private String name;
    private String lastName;
    private String type_doc;
    private int doc;

    public Client() { }

    public Client(int id, String name, String lastName, String type_doc, int doc) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.type_doc = type_doc;
        this.doc = doc;
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

    public String getType_doc() {
        return type_doc;
    }

    public void setType_doc(String type_doc) {
        this.type_doc = type_doc;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    @Override
    public String toString() {
        return "El id del cliente es: " + id +
                ". El nombre del cliente es: " + name +
                ". El apellido del cliente es: " + lastName +
                ". El tipo del documento es: " + type_doc +
                ". El numero de documento es: " + doc;
    }
}
