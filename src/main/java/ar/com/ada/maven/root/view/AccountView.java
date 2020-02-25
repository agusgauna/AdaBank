package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.utils.CommandLineTable;
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

    public static Boolean getResponseToDelete(Account account) {
        System.out.print("Se Eliminará la siguiente cuenta: ");


        System.out.println("¿Esta seguro que desea eliminarla? ");
        System.out.println("| 1 | Si");
        System.out.println("| 2 | No");

        Scanner keyboard = Singletone.getInstance();
        keyboard.nextLine();


        while (true) {
            try {
                System.out.print("? ");
                String name = keyboard.nextLine().trim();
                while (!name.matches("^[1-2]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar una opcion valida");
                    name = keyboard.nextLine();
                }
                return "1".equals(name);
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar una opcion valida");
                keyboard.next();
            }
        }
    }
    public void deleteAccountCanceled() {
        System.out.println("Ha cancelado la eliminacion de la cuenta\n");
        Singletone.pressEnterKeyToContinue();
    }

    public void showDeleteAccount(Integer number) {
        System.out.println("La cuenta " + number + " se ha eliminado exitosamente");
        Singletone.pressEnterKeyToContinue();
    }


    public void showNewAccount(Account account){
        System.out.println("Su número de cuenta corriente es: " + account.getNumber());
        Singletone.pressEnterKeyToContinue();
    }

    public void accountAlreadyExist(Integer number){
        System.out.println("Este número de cuenta ya existe en la base de datos");
        Singletone.pressEnterKeyToContinue();
    }


    public void newAccountCanceled() {
        System.out.println(" Se ha cancelado el proceso de apertura de cuenta");
    }

    public String printAccountsPerPage(List<Account> accounts, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("\n+----------------------------------------+");
            System.out.println("\t\t ADA BANK :: Modulo Cuentas :: Lista de Cuentas");
            System.out.println("+----------------------------------------+\n");
        }

        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);

        st.setHeaders("ID", "CUENTA", "SALDO", "CLIENTE", "TIPO DE CUENTA", "SUCURSAL");
        accounts.forEach(accountDTO ->
                st.addRow(
                        accountDTO.getId().toString(),
                        accountDTO.getNumber(),
                        String.valueOf(accountDTO.getBalance()),
                        accountDTO.getClient().toString(),
                        accountDTO.getAccount_type().toString(),
                        accountDTO.getBranch().toString())
        );
        st.print();

        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionEdithOrDelete);

        System.out.println("\n+----------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------+");

        return Singletone.getInputString();
    }

 


}



