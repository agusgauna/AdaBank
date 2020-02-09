package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.utils.Singletone;

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

    public String insertNewAccount() {
        System.out.println(" Usted ingresará una nueva cuenta ");
        System.out.println(" Ingrese numero de DNI // Si desea cancelar, no ingrese datos");

        Scanner keyboard2 = Singletone.getInstance();
        keyboard2.nextLine();

        while (true) {
            try {
                int doc = Integer.parseInt(keyboard2.nextLine().trim());
                while (!doc.matches("^[0-9IiAaSsUuEe]+$") && !doc.isEmpty()) {
                    System.out.println(" Usted debe ingresar una opción válida ");
                    doc = Integer.parseInt(keyboard2.nextLine());
                }
                return doc;
            } catch (InputMismatchException e) {
                System.out.println("Usted debe ingresar una opción válida");
                keyboard2.nextLine();

            }

        }


    }

    public void newAccountCanceled() {
        System.out.println(" Se ha cancelado el proceso de apertura de cuenta");
    }
}
