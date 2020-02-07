package ar.com.ada.maven.root.utils;

import java.util.Scanner;

public class Singletone {
    private static Scanner scanner;

    private Singletone() {}

    public static Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void pressEnterKeyToContinue() {
        System.out.println("Presione la tecla Enter para continuar.");
        try {
            System.out.println();
        } catch (Exception e) {
            System.out.println("ERROR MESSAGE: " + e.getMessage());
        }
    }
}
