package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.AccountDAO;
import ar.com.ada.maven.root.model.dao.AccountTypeDAO;
import ar.com.ada.maven.root.model.dao.BranchDAO;
import ar.com.ada.maven.root.model.dao.ClientDAO;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.AccountType;
import ar.com.ada.maven.root.model.dto.Branch;
import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.utils.IbanGenerator;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.view.AccountView;
import ar.com.ada.maven.root.view.MainView;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AccountController {
    private static AccountView view = new AccountView();
    private static AccountDAO accountDAO = new AccountDAO(false);
    private static ClientDAO clientDAO = new ClientDAO(false);
    private static AccountTypeDAO accountTypeDAO = new AccountTypeDAO(false);
    private static BranchDAO branchDAO = new BranchDAO(false);
    private static void assertEquals(String s, String padStart) {
    }

    public static void init() {
        boolean out = false;

        while (!out) {
            int option = view.AccountMenuSelectOption();
            switch (option) {
                case 1:
                    printAllAccounts();
                    break;
                case 2:
              //      createNewAccount();
                    break;
                case 3:
                  //  deleteAccount();
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

    public static void createNewAccount(Branch branch, AccountType accountType, Client client) {

         String number;
         number = assertEquals("0000123456", Strings.padStart("123456", 10, '0'));


        Integer clientId = ClientController.listClientsPerPage(Paginator.SELECT, false);
        Integer accountTypeId = AccountTypeController.listAccountsTypePerPage(Paginator.SELECT, false);
        Integer branchId = BranchController.listBranchsPerPage(Paginator.SELECT, false);



        if (clientId != 0 && accountTypeId != 0 && branchId != 0) {

        }

        Account accountByNumber = accountDAO.findByNumberAccount(number); //   parametro
            Client clienteById = clientDAO.findById(clientId);
            AccountType accountTypeById = accountTypeDAO.findById(accountTypeId);
            Branch branchById = branchDAO.findById(branchId);

            Account newAccount = new Account(/*nuevoNumCuenta, clienteById, accountTypeById, branchById*/); // q parametros??

            if (accountByNumber != null && accountByNumber.equals(newAccount)) {
                view.accountAlreadyExist(newAccount.getNumber());
            } else {
                Boolean isSaved = accountDAO.save(newAccount);
                if (isSaved) {
                    view.showNewAccount(newAccount);
            }else {
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

        Integer branchCode = branch.getCode();
        Integer accountTypeCode = accountType.getCode_control();
        Integer codigoCuentaCliente = newControlNumberAccount;

        assertEquals("0000123456", Strings.padStart("123456", 10, '0'));

        String numberAccount = iban + bankCode + branchCode + accountTypeCode + newControlNumberAccount;


        numberData.put("number", numberAccount);
        numberData.put("control", String.valueOf(newControlNumberAccount));

        return numberData;
    }

    private static Account getAccountToDelete(String optionDelete) {
        boolean hasExitWhile = false;
        Account accountToDelete = null;

        String actionInfo = Paginator.DELETE.equals(optionDelete) ? "Eliminar": "Eliminar";

        view.selectAccountIdToEdithOrDeleteInfo(actionInfo);

        int accountIdToDelete = listAccountsPerPage(optionDelete, true);

        if (accountIdToDelete != 0) {
            while (!hasExitWhile) {
                accountToDelete = accountDAO.findById(accountIdToDelete);
                if (accountToDelete == null) {
                    view.accountNotExist(accountIdToDelete);
                    accountIdToDelete = view.clientIdSelected(optionDelete);
                    hasExitWhile = (accountIdToDelete == 0);
                } else {
                    hasExitWhile = true;
                }
            }
        }

        return accountToDelete;
    }
    private static void deleteAccount(int id) {
        Account account = accountDAO.findById(id);
        if (account != null) {
            Boolean toDelete = view.getResponseToDelete(account);
            if (toDelete) {

                Boolean isDelete = accountDAO.delete(id);

                if (isDelete)
                    view.showDeleteAccount(account.getId());
            } else
                view.newAccountCanceled();

        }


    }


/*
}
