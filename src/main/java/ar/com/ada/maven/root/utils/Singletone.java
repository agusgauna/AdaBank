package ar.com.ada.maven.root.utils;

import ar.com.ada.maven.root.view.MainView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Singletone {
    private static Scanner scanner;

    private Singletone() {
    }

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

    public static String getInputString() {
        Scanner scanner = getInstance();
        while (true) {
            try {
                String txt = scanner.nextLine().trim();
                while (!txt.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !txt.isEmpty()) {
                    MainView.invalidData();
                    txt = scanner.nextLine();
                }
                return txt;
            } catch (InputMismatchException e) {
                MainView.invalidData();
                scanner.next();
            }
        }
    }

    public static String getInputInteger() {
        Scanner scanner = getInstance();
        while (true) {
            try {
                System.out.print("? ");
                String txt = scanner.nextLine().trim();
                while (!txt.matches("^[0-9]+$") && !txt.isEmpty()) {
                    MainView.invalidData();
                    txt = scanner.nextLine();
                }
                return txt;
            } catch (InputMismatchException e) {
                MainView.invalidData();
                scanner.next();
            }
        }

    }

}