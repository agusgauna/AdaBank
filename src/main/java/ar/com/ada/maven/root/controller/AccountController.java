package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.AccountDAO;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.AccountType;
import ar.com.ada.maven.root.model.dto.Branch;
import ar.com.ada.maven.root.model.dto.Client;
import ar.com.ada.maven.root.utils.IbanGenerator;
import ar.com.ada.maven.root.view.AccountView;
import ar.com.ada.maven.root.view.MainView;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AccountController {
    private static AccountView view = new AccountView();
    private static AccountDAO accountDAO = new AccountDAO(false);
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


    public static void createNewAccount(Branch branch, AccountType accountType) {

        Account newAccount = new Account();
        Client client = new Client();





        Account lastAccount = accountDAO.getLastAccount();
        Integer ultimoNumeroCuenta = lastAccount.getControlNumber();
        Integer nuevoNumCuenta = ultimoNumeroCuenta + 1;
/*
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
        }*/
    }

    private HashMap<String, String> generateNewNumberAccount(Branch branch, AccountType accountType){
        HashMap<String, String> numberData = new HashMap<>();
        Account lastAccount = accountDAO.getLastAccount();
        Integer newControlNumberAccount = lastAccount.getControlNumber() + 1;

        String iban = branch.getBank().getCountry().getCode();
        //TODO transformar este tipo de dato en string para reconocer los ceros a la izquierda
        Integer bankCode = branch.getBank().getCode();
        //TODO transformar este tipo de dato en string para reconocer los ceros a la izquierda
        Integer branchCode = branch.getCode();
        Integer accountTypeCode =  accountType.getCode_control();
        Integer codigoCuentaCliente = newControlNumberAccount;

    assertEquals("0000123456", Strings.padStart("123456", 10, '0'));

        String numberAccount = iban + bankCode + branchCode + accountTypeCode + newControlNumberAccount;


        numberData.put("number", numberAccount);
        numberData.put("control", String.valueOf(newControlNumberAccount));

        return  numberData;
    }




}
