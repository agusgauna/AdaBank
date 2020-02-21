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
        return Integer.valueOf(Singletone.getInputInteger());
    }
    public static void invalidData () {
        System.out.println("Debe ingresar un dato válido");
    }
}
