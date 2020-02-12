package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.ClientDAO;
import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.view.ClientView;

import java.util.HashMap;
import java.util.List;

public class ClientController {
    private static ClientView view = new ClientView();
    private static ClientDAO clientDAO = new ClientDAO();

    private static void clientList() {
        List<Client> clients = clientDAO.findAll();
        view.printAllClient(clients);
    }

    public static void init() {
        boolean a = false;
        while(!a) {
            int option = view.clientMenuSelectOption();
            switch (option) {
                case 1:
                    clientList();
                    break;
                case 2:
                    createNewClient();
                    break;
                case 3:
                    edithClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    a = true;
                    break;
                default:
                    System.out.println("Debe seleccionar una opción valida");
            }
        }
    }
    public static void createNewClient() {
        HashMap<String, String> dataClient = view.getNameNewClient();
        if (!dataClient.isEmpty()) {
            String name =  dataClient.get("name");
            String lastName = dataClient.get("last_name");
            String typeDoc = dataClient.get("type_doc");
            Integer doc = Integer.valueOf(dataClient.get("doc"));

            Client newClient = new Client(dataClient);
            Client byName = clientDAO.findByName(dataClient);

            if (byName != null && byName.getName().toLowerCase().equals(newClient.getName().toLowerCase())) {
                view.clientAlreadyExists(newClient.getName());
            } else {
                Boolean resultado = clientDAO.save(newClient);
                if (resultado)
                    view.showNewClient(newClient.getName());
            }
        } else {
            view.newClientCanceled();
        }
    }
}
