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
                "3) Editar \n " +
                "4) Eliminar \n " +
                "5) Salir ");

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


        // iban+cod bco + cod suc + cod control + cod cuenta
        public void createNumberAccount(){


        }



    public void newAccountCanceled() {
        System.out.println(" Se ha cancelado el proceso de apertura de cuenta");
    }
}



