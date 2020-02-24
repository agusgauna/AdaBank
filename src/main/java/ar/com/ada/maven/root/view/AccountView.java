package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.utils.Singletone;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountView {

    public int AccountMenuSelectOption() {

        System.out.println("*** Bienvenido al menú Cuentas*** \n \t Seleccione una opción: \n" +
                "1) Lista \n " +
                "2) Agregar \n " +
                "3) Eliminar \n " +
                "4) Salir ");

        Scanner keyboard = Singletone.getInstance();

        while (true) {

            try {

                int choice = keyboard.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("La opción ingresada debe ser valida");
                keyboard.next();
            }
        }
    }

    public void printAllAccounts(List<Account> accounts) {
        System.out.println("Listado de Cuentas");
        accounts.forEach(cuentas -> System.out.println(" id: " + cuentas.getId() + "  || nombre: " + cuentas.getClient()));
    }


        public HashMap<String, String> insertNewAccount () {
            Scanner keyboard2 = Singletone.getInstance();
            HashMap<String, String> data = new HashMap<>();

            System.out.println(" Usted ingresará una nueva cuenta ");
            System.out.println(" Ingrese numero de DNI: ");
            data.put("doc", Singletone.getInputInteger());

            return data;
        }



    public void AccountAlreadyExists(Integer doc) {
        System.out.println("La cuenta ya existe en base de datos");
        Singletone.pressEnterKeyToContinue();
    }
    public void newAccountCanceled() {
        System.out.println(" Se ha cancelado el proceso de apertura de cuenta");
    }

    public void showNewAccount(Account account) {

        System.out.println("Su número de cuenta es :" + account.getNumber() );
        Singletone.pressEnterKeyToContinue();


    }


}



