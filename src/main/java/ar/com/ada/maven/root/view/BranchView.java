package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Branch;
import ar.com.ada.maven.root.utils.CommandLineTable;
import ar.com.ada.maven.root.utils.Paginator;
import ar.com.ada.maven.root.utils.Singletone;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BranchView {
    private Scanner scanner = Singletone.getInstance();
    public String printBranchsPerPage(List<Branch> branchs, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("La lista de sucursales es: ");
        }

        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);

        st.setHeaders("ID", "NOMBRE", "CODIGO");
        branchs.forEach(branch ->
                st.addRow(branch.getId().toString(), branch.getName(), branch.getCode())
        );
        st.print();

        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionEdithOrDelete);

        System.out.println("\n+----------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------------------------------+\n");

        return String.valueOf(Singletone.getInputString());
    }
    public int branchIdSelected(String actionOption) {
        switch (actionOption) {
            case Paginator.EDITH:
                actionOption = "editar";
                break;
            case Paginator.DELETE:
                actionOption = "eliminar";
                break;
            case Paginator.SELECT:
                actionOption = "elegir";
                break;
        }
        System.out.println("Ingrese el numero de ID de la sucural para " + actionOption + " ó 0 para cancelar: ");

        while (true) {
            try {
                System.out.print("? ");
                int choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un id valido");
                scanner.next();
            }
        }
    }
}