package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.AccountDAO;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.utils.IbanGenerator;
import ar.com.ada.maven.root.view.AccountView;
import ar.com.ada.maven.root.view.MainView;

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


    public static void createNewAccount(Account account) {

        Account newAccount = new Account();
        Account lastAccount = accountDAO.getLastAccount();
        Integer ultimoNumeroCuenta = lastAccount.getControlNumber();
        Integer nuevoNumCuenta = ultimoNumeroCuenta + 1;

        String iban; //= account.getBranch().getBank().getCountry().getCode();
        Integer code = account.getBranch().getBank().getCode();
        Integer codeBranch = account.getBranch().getCode();
        Integer codeControl = account.getAccount_type().getCode_control();
        newAccount.setBranch(account.getBranch());
        newAccount.setAccount_type(account.getAccount_type());
        newAccount.setControlNumber(nuevoNumCuenta);

        iban = IbanGenerator.Generation(account.getBranch().getBank().getCountry().getCode(), code, codeBranch, account.getAccount_type().getCode_control(), codeControl);
        newAccount.setNumber(iban);

        Boolean resultado = accountDAO.save(newAccount);
        if (resultado)
            view.showNewAccount(newAccount);

        else {
            view.newAccountCanceled();
        }
    }

}
