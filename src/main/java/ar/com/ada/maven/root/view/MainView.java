package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.utils.Singletone;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    public int mainMenuSelectOption() {
        System.out.println("Bienvenido a AdaBank");
        System.out.println("Para empezar a operar, debe seleccionar una opción: ");
        System.out.println("| 1 | Cliente");
        System.out.println("| 2 | Cuenta");
        System.out.println("| 3 | Movimientos");

        Scanner keyboard = Singletone.getInstance();

        while (true) {
            try {
                System.out.println("?");
                int choice = keyboard.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese una opción válida");
                keyboard.next();
            }
        }
    }
}
