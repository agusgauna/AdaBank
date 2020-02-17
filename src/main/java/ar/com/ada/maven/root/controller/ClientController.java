package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.ClientDAO;
import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.view.ClientView;
import ar.com.ada.maven.root.view.MainView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientController {
    private static ClientView view = new ClientView();
    private static ClientDAO clientDAO = new ClientDAO();


    public static void init() {
        boolean shouldGetOut = false;
        while (!shouldGetOut) {
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
                    shouldGetOut = true;
                    break;
                default:
                    MainView.invalidData();
            }
        }
    }

    private static void clientList() {
        List<Client> clients = clientDAO.findAll();
        view.printAllClient(clients);
    }

    public static void createNewClient() {
        HashMap<String, String> dataClient = view.getNameNewClient();
        if (!dataClient.isEmpty()) {
            String name = dataClient.get("name");
            String lastName = dataClient.get("last_name");
            String typeDoc = dataClient.get("type_doc");
            Integer doc = Integer.valueOf(dataClient.get("doc"));

            Client newClient = new Client(name, lastName, typeDoc, doc);
            Client byDoc = clientDAO.findByDoc(doc);

            if (byDoc != null) {
                view.clientAlreadyExists(newClient.getDoc());
            } else {
                Boolean resultado = clientDAO.save(newClient);
                if (resultado)
                    view.showNewClient(newClient);
            }
        } else {
            view.newClientCanceled();
        }
    }
    private static int listCountriesPerPage(String optionSelectEdithOrDelete, boolean showHeader) {
        int limit = 4, currentPage = 0, totalClients, totalPages, clientIdSelected = 0;
        List<Client> clients;
        List<String> paginator;
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalClients = clientDAO.getTotalClients();
            totalPages = (int) Math.ceil((double) totalClients / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            clients = clientDAO.findAll(limit, currentPage * limit);
            String choice = view.printAllClient( List<Client> clients);

            switch (choice) {
                case "i":
                case "I":
                    currentPage = 0;
                    break;
                case "a":
                case "A":
                    if (currentPage > 0) currentPage--;
                    break;
                case "s":
                case "S":
                    if (currentPage + 1 < totalPages) currentPage++;
                    break;
                case "u":
                case "U":
                    currentPage = totalPages - 1;
                    break;
                case "e":
                case "E":
                    if (optionSelectEdithOrDelete != null) {
                        clientIdSelected = view.clientIdSelected(optionSelectEdithOrDelete);
                        shouldGetOut = true;
                    }
                    break;
                case "q":
                case "Q":
                    shouldGetOut = true;
                    break;
                default:
                    if (choice.matches("^-?\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else MainView.invalidData();
            }
        }
        return clientIdSelected;
    }

    private static void edithClient() {
        Client clientToEdith = getClientToEdithOrDelete(Paginator.EDITH);

        if (clientToEdith != null) {
            String nameToUpdate = view.getNameToUpdate(clientToEdith);

            if (nameToUpdate.isEmpty()) {
                view.updateClientCanceled();
            } else {
                Client clientToUpdate = getClientToUpdate(clientToEdith);

                clientToEdith.setDoc(docToUpdate);

                Boolean isUpdate = clientDAO.update(clientToEdith);

                if (isUpdate)
                    view.showUpdateClient(clientToEdith);
            }

        } else {
            view.updateClientCanceled();
        }
    }

    private static Client getClientToEdithOrDelete(String optionEdithOrDelete) {
        boolean hasExitWhile = false;
        Client clientToEdithOrDelete = null;

        String actionInfo = Paginator.EDITH.equals(optionEdithOrDelete) ? "Editar" : "Eliminar";

        view.selectClientIdToEdithOrDeleteInfo(actionInfo);

        int clientIdToEdith = listClientsPerPage(optionEdithOrDelete, true);

        if (clientIdToEdith != 0) {
            while (!hasExitWhile) {
                clientToEdithOrDelete = clientDAO.findById(clientIdToEdith);
                if (clientToEdithOrDelete == null) {
                    view.clientNotExist(clientIdToEdith);
                    clientIdToEdith = view.clientIdSelected(optionEdithOrDelete);
                    hasExitWhile = (clientIdToEdith == 0);
                } else {
                    hasExitWhile = true;
                }
            }
        }

        return clientToEdithOrDelete;
    }

    private static Client getClientToUpdate(Client clientToEdith) {
        boolean hasExitWhile = false;
        Client clientById = null;

        view.selectClientIdToEdithInfo("Actualizar");

        int clientIdSelected = ClientController.clientListPerPage(Paginator.SELECT, false);

        if (clientIdSelected != 0) {
            while (!hasExitWhile) {
                clientById = clientDAO.findById(clientIdSelected);
                if (clientById == null) {
                    view.clientNotExist(clientIdSelected);
                    clientIdSelected = view.clientIdSelected(Paginator.SELECT);
                    hasExitWhile = (clientIdSelected == 0);
                } else {
                    hasExitWhile = true;
                }
            }
        }

        return clientById != null && !clientToEdith.getClient().equals(clientById) ?
                clientByIdById :
                clientToEdith.getClient();
    }

    private static void deleteClient() {
        Client clientToDelete = getClientToEdithOrDelete(Paginator.DELETE);

        if (clientToDelete != null) {
            Boolean toDelete = view.getResponseToDelete(clientToDelete);
            if (toDelete) {
                Boolean isDeleted = clientDAO.delete(clientToDelete.getId());

                if (isDeleted)
                    view.showDeleteClient(clientToDelete.getName());
            }

        } else {
            view.deleteClientCanceled();
        }
    }
}
}
