package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.utils.Singletone;
import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClientView {
    public int clientMenuSelectOption() {
        System.out.println("Ud ha ingresado al menú Cliente");
        System.out.println("Las opciones disponibles son: 1.Listar 2. Agregar 3.Editar 4.Eliminar 5. Salir ");
        Scanner scanner = Singletone.getInstance();
        while (true) {
            try {
                int choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese una opción válida");
                scanner.next();
            }
        }
    }
    public void printAllClient(List<Client> clients) {
        System.out.println("El listado de los clientes es: ");
        clients.forEach(client -> {
            Integer id = client.getId();
            String name = client.getName();
            String lastName = client.getLastName();
            String typeDoc = client.getType_doc();
            Integer dni = client.getDoc();
            System.out.println("El id es: " + id + ". El nombre es: " + name+ ". El apellido es: " +lastName+ ". El tipo de documento es: " + typeDoc+ ". El documento es:" + dni);
        });
    }

    public HashMap<String, String> getNameNewClient() {
        Scanner scanner = Singletone.getInstance();
        HashMap<String, String> data = new HashMap<>();

        System.out.println("Para agregar un cliente.");
        System.out.println("Ingrese Nombre: ");
        data.put("name", Singletone.getInputString());
        System.out.println("Ingrese Apellido: ");
        data.put("last_name", Singletone.getInputString());
        System.out.println("Ingrese tipo de documento: ");
        data.put("type_doc", Singletone.getInputString());
        System.out.println("Ingrese documento: ");
        data.put("doc", Singletone.getInputInteger());

        return data;
    }
    public void showNewClient(Client client) {
        System.out.println("El cliente nuevo es: " +client.getLastName()+ ", " +client.getName());
        Singletone.pressEnterKeyToContinue();
    }

    public void newClientCanceled() {
        System.out.println("El cliente no se ha podido agregar");
        Singletone.pressEnterKeyToContinue();
    }

}
