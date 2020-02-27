package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.AccountDAO;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.AccountType;
import ar.com.ada.maven.root.model.dto.Branch;
import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.view.AccountView;
import ar.com.ada.maven.root.view.MainView;

import java.util.HashMap;

import java.util.List;

public class AccountController {

    private static AccountView view = new AccountView();
    private static AccountDAO accountDAO = new AccountDAO(false);
    private static ClientDAO clientDAO = new ClientDAO(false);
    private static AccountTypeDAO accountTypeDAO = new AccountTypeDAO(false);
    private static BranchDAO branchDAO = new BranchDAO(false);

 

    public static void init() {
        boolean out = false;

        while (!out) {
            int option = view.AccountMenuSelectOption();
            switch (option) {
                case 1:
                    printAllAccounts();
                    break;
                case 2:
                    //createNewAccount();
                    break;
                case 3:
                    //deleteAccount();
                    break;
                case 4:
                    out = true;
                    MainView.invalidData();
            }

        }
    }

    private static void printAllAccounts() {

        printAccountsPerPage(null, true);
    }

    private static int printAccountsPerPage(String optionSelectEdithOrDelete, boolean showHeader) {
        int limit = 4, currentPage = 0, totalAccounts, totalPages, customerIdSelected = 0;
        List<Account> accounts;
        List<String> paginator;
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalAccounts = accountDAO.getTotalAccounts();
            totalPages = (int) Math.ceil((double) totalAccounts / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);
            accounts = accountDAO.findAll(limit, currentPage * limit);
            String choice = view.printAccountsPerPage(accounts, paginator, optionSelectEdithOrDelete, showHeader);

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
                        customerIdSelected = view.accountIdSelected(optionSelectEdithOrDelete);
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
        return customerIdSelected;
    }

    public static void createNewAccount(Client client, AccountType accountType, Branch branch, Number number) {


        Integer clientId = ClientController.listClientsPerPage(Paginator.SELECT, false);
        Integer accountTypeId = AccountTypeController.listAccountTypesPerPage(Paginator.SELECT, false);
        Integer branchId = BranchController.listBranchPerPage(Paginator.SELECT, false);

        Account newAccount = new Account(client, accountType, branch, number);
        Boolean cuentanueva = accountDAO.save(newAccount);


        if (clientId != 0 && accountTypeId != 0 && branchId != 0) {
        }

        if (newAccount != null && newAccount.equals(newAccount)) {
            view.accountAlreadyExist(newAccount.getNumber());
        } else {
            Boolean isSaved = accountDAO.save(newAccount);
            if (isSaved) {
                view.showNewAccount(newAccount);
            } else {
                view.newAccountCanceled();
            }


        }

    }

    private HashMap<String, String> generateNewNumberAccount(Branch branch, AccountType accountType) {
        HashMap<String, String> numberData = new HashMap<>();
        Account lastAccount = accountDAO.getLastAccount();
        Integer newControlNumberAccount = lastAccount.getControlNumber() + 1;

        String iban = branch.getBank().getCountry().getCode();
        Integer bankCode = branch.getBank().getCode();

        String branchCode = branch.getCode();
        Integer accountTypeCode = accountType.getCode_control();
        Integer codigoCuentaCliente = newControlNumberAccount;

        String padStart = Strings.padStart("123456", 10, '0');

        String numberAccount = iban + bankCode + branchCode + accountTypeCode + newControlNumberAccount;


        Boolean resultado = accountDAO.save(newAccount);
        if (resultado)
            view.showNewAccount(newAccount);


        numberData.put("number", numberAccount);
        numberData.put("control", String.valueOf(newControlNumberAccount));

        return numberData;
    }

    private static Account getAccountToEdithOrDelete(String optionEdithOrDelete) {
        boolean hasExitWhile = false;
        Account accountToEdithOrDelete = null;

        String actionInfo = Paginator.EDITH.equals(optionEdithOrDelete) ? "Editar" : "Eliminar";

        view.selectAccountIdToEdithOrDeleteInfo(actionInfo);

        int accountIdToEdith = printAccountsPerPage(optionEdithOrDelete, true);

        if (accountIdToEdith != 0) {
            while (!hasExitWhile) {
                accountToEdithOrDelete = accountDAO.findById(accountIdToEdith);
                if (accountToEdithOrDelete == null) {
                    view.accountNotExist(accountIdToEdith);
                    accountIdToEdith = view.accountIdSelected(optionEdithOrDelete);
                    hasExitWhile = (accountIdToEdith == 0);
                } else {
                    hasExitWhile = true;
                }
            }
        }

        return accountToEdithOrDelete;
    }

    private static void deleteAccount() {
        Account accountToDelete = getAccountToEdithOrDelete(Paginator.DELETE);

        if (accountToDelete != null) {
            Boolean toDelete = view.getResponseToDelete(accountToDelete);
            if (toDelete) {
                Boolean isDeleted = accountDAO.delete(accountToDelete.getId());

                if (isDeleted)
                    view.showDeleteAccount(accountToDelete.getNumber());
            }

        } else {
            view.deleteAccountCanceled();
        }

    }

}
