package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.AccountDAO;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.view.AccountView;
import ar.com.ada.maven.root.view.MainView;

import java.util.HashMap;
import java.util.List;

public class AccountController {
    private static AccountView view = new AccountView();
    private static AccountDAO accountDAO = new AccountDAO(false);



    public static void init() {
        boolean out = false;

        while (!out) {
            int option = view.AccountMenuSelectOption();
            switch (option) {
                case 1:
                    printAllAccounts();
                    break;
                case 2:
            createNewAccount();
            break;
                case 3:
            deleteAccount();
            break;
                case 4:
                    out = true;
                    MainView.invalidData();
            }

        }
    }

    private static void printAllAccounts() {
        List<Account> cuentas = accountDAO.findAll();
        view.printAllAccounts(cuentas);
    }

    public static void createNewAccount(){

        HashMap <String, String> dataAccount = view.insertNewAccount();
        if(!dataAccount.isEmpty()){
            Integer doc = Integer.valueOf(dataAccount.get("doc"));

            Account newAccount = new Account(dataAccount);

        }
    }

}
