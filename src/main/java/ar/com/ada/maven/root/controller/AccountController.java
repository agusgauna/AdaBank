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
    private void assertEquals(String s, String padStart) {
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
            String choice = view.printAllAccounts(accounts, paginator, optionSelectEdithOrDelete, showHeader); //*revisar

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
                        customerIdSelected = view.clientIdSelected(optionSelectEdithOrDelete);
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

    public static void createNewAccount(Branch branch, AccountType accountType) {





    }

    private HashMap<String, String> generateNewNumberAccount(Branch branch, AccountType accountType) {
        HashMap<String, String> numberData = new HashMap<>();
        Account lastAccount = accountDAO.getLastAccount();
        Integer newControlNumberAccount = lastAccount.getControlNumber() + 1;

        String iban = branch.getBank().getCountry().getCode();
        //TODO transformar este tipo de dato en string para reconocer los ceros a la izquierda
        Integer bankCode = branch.getBank().getCode();
        //TODO transformar este tipo de dato en string para reconocer los ceros a la izquierda
        Integer branchCode = branch.getCode();
        Integer accountTypeCode = accountType.getCode_control();
        Integer codigoCuentaCliente = newControlNumberAccount;

        assertEquals("0000123456", Strings.padStart("123456", 10, '0'));

        String numberAccount = iban + bankCode + branchCode + accountTypeCode + newControlNumberAccount;


        numberData.put("number", numberAccount);
        numberData.put("control", String.valueOf(newControlNumberAccount));

        return numberData;
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

    Account lastAccount = accountDAO.getLastAccount();
    Integer ultimoNumeroCuenta = lastAccount.getControlNumber();
    Integer nuevoNumCuenta = ultimoNumeroCuenta + 1;
/*
}
