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
        listClientsPerPage(null, true);
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
    private static void edithClient() {
        int clientIdToEdith = listClientsPerPage(Paginator.EDITH, true);
        if (clientIdToEdith != 0)
            editSelectedClient(clientIdToEdith);
        else
            view.updateClientCanceled();
    }

    private static int listClientsPerPage(String optionSelectEdithOrDelete, boolean showHeader) {
        int limit = 4, currentPage = 0, totalClients, totalPages, clientIdSelected = 0;
        List<Client> clients;
        List<String> paginator;
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalClients = clientDAO.getTotalClients();
            totalPages = (int) Math.ceil((double) totalClients / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            clients = clientDAO.findAll(limit, currentPage * limit);
            String choice = view.printClientsPerPage(clients, paginator, optionSelectEdithOrDelete, showHeader);

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

    private static void editSelectedClient(int id) {
        Client clientById = clientDAO.findById(id);
        if (clientById != null) {
            String nameToUpdate = view.getNameToUpdate(clientById);
            String lastName = view.getNameToUpdate(clientById);
            if (!nameToUpdate.isEmpty()) {
                clientDAO.findByName(nameToUpdate, lastName);
                clientById.setName(nameToUpdate);
                clientById.setLastName(nameToUpdate);

                Boolean isSaved = clientDAO.update(clientById, id);

                if (isSaved)
                    view.showUpdateClient(clientById);
            } else
                view.updateClientCanceled();
        } else {
            view.clientNotExist(id);
            int clientIdSelected = view.clientIdSelected("Editar");
            if (clientIdSelected != 0)
                editSelectedClient(clientIdSelected);
            else
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

    private static void deleteClient() {
        Client clientToDelete = getClientToEdithOrDelete(Paginator.DELETE);

        if (clientToDelete != null) {
            Boolean toDelete = view.getResponseToDelete(clientToDelete);
            if (toDelete) {
                Boolean isDeleted = clientDAO.delete(clientToDelete.getId());

                if (isDeleted)
                    view.showDeleteClient(clientToDelete.getLastName(), clientToDelete.getName());
            }

        } else {
            view.deleteClientCanceled();
        }
    }
}

