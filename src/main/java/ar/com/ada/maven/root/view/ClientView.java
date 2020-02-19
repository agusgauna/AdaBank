package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.utils.Ansi;
import ar.com.ada.maven.root.utils.CommandLineTable;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.utils.Singletone;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClientView {
    private Scanner scanner = Singletone.getInstance();

    public int clientMenuSelectOption() {
        System.out.println("Ud ha ingresado al menú Cliente");
        System.out.println("Las opciones disponibles son: ");
        System.out.println("| 1 | Listar");
        System.out.println("| 2 | Agregar");
        System.out.println("| 3 | Editar");
        System.out.println("| 4 | Eliminar");
        System.out.println("| 5 | Salir");
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
    public String printClientsPerPage(List<Client> clients, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("La lista de clientes es: ");
        }

        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);

        st.setHeaders("ID", "NOMBRE", "APELLIDO");
        clients.forEach(client ->
                st.addRow(client.getId().toString(), client.getName(), client.getLastName())
        );
        st.print();

        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionEdithOrDelete);

        System.out.println("\n+----------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------------------------------+\n");

        while (true) {
            try {
                System.out.print("? ");
                String name = scanner.nextLine().trim();
                while (!name.matches("^[0-9IiAaSsUuEeqQ]+$") && !name.isEmpty()) {
                    MainView.invalidData();
                    System.out.print("? ");
                    name = scanner.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                MainView.invalidData();
                scanner.next();
            }
        }
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
        System.out.println("El cliente nuevo es: " +client.getLastName()+ ", " +client.getName()+ ". El DNI es: " +client.getDoc());
        Singletone.pressEnterKeyToContinue();
    }

    public void newClientCanceled() {
        System.out.println("El cliente no se ha podido agregar");
        Singletone.pressEnterKeyToContinue();
    }

    public void clientAlreadyExists(Integer doc) {
        System.out.println("El cliente ya existe en la base de datos");
        Singletone.pressEnterKeyToContinue();
    }

    public void updateClientCanceled() {
        System.out.println("Ha cancelado la actualizacion del Cliente");
        Singletone.pressEnterKeyToContinue();
    }

    public void clientNotExist(int id) {
        System.out.println("No existe un cliente con el id " + id + " asociado");
        System.out.println("Selecciones un ID valido o 0 para cancelar");
    }

    public String getNameToUpdate(Client client) {
        System.out.print("Se actualizará el nombre del siguiente cliente: ");
        System.out.println( client.getName() + " " + client.getLastName()+ " ");

        System.out.print("Ingrese el nuevo nombre del cliente para actualizar ");
        System.out.println("(para cancelar, no ingresar datos y presionar enter)");

        scanner.nextLine();

        while (true) {
            try {
                System.out.print("? ");
                String name = scanner.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()) {
                    MainView.invalidData();
                    name = scanner.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                MainView.invalidData();
                scanner.next();
            }
        }
    }

    public static void selectClientIdToEdithOrDeleteInfo(String actions) {
        System.out.println("De la siguiente lista de clientes, seleccione el id para  " + actions);
        Singletone.pressEnterKeyToContinue();
    }

    public void showUpdateClient(Client client) {
        System.out.println("El cliente " + client.getLastName() + ", " + client.getName() + ". DNI: " +client.getDoc()+ "se ha actualizado exitosamente");
        Singletone.pressEnterKeyToContinue();
    }

    public int clientIdSelected(String actionOption) {
        switch (actionOption) {
            case Paginator.EDITH:
                actionOption = "editar";
                break;
            case Paginator.DELETE:
                actionOption = "eliminar";
                break;
            case Paginator.SELECT:
                actionOption = "elegir";
                break;
        }
        System.out.println("Ingrese el numero de ID del cliente para " + actionOption + " ó 0 para cancelar: ");

        while (true) {
            try {
                System.out.print("? ");
                int choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un id valido");
                scanner.next();
            }
        }
    }

    public Boolean getResponseToDelete(Client client) {
        System.out.print("Se Eliminará el siguiente cliente: ");
        System.out.println( + client.getId() + " " + client.getLastName() + ", " + client.getName());


        System.out.println("Esta seguro que desea eliminarlo? ");
        System.out.println("| 1 | Si");
        System.out.println("| 2 | No");

        scanner.nextLine();

        while (true) {
            try {
                System.out.print("? ");
                String name = scanner.nextLine().trim();
                while (!name.matches("^[1-2]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar una opcion valida");
                    name = scanner.nextLine();
                }
                return "1".equals(name);
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar una opcion valida");
                scanner.next();
            }
        }
    }

    public void showDeleteClient(String lastName, String name) {
        System.out.println("El cliente " + lastName + ", " +name+ " se ha eliminado exitosamente");
        Singletone.pressEnterKeyToContinue();
    }

    public void deleteClientCanceled() {
        System.out.println("Ha cancelado la eliminacion del Cliente");
        Singletone.pressEnterKeyToContinue();
    }
}

