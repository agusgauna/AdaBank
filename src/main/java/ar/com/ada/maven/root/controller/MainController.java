package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.view.MainView;

public class MainController {
    private static MainView view = new MainView();

    public static void run() {
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            int option = view.mainMenuSelectOption();
            switch (option) {
                case 1:
                    ClientController.init();
                    break;
                case 2:
                    shouldGetOut = true;
                    break;
                default:
                    System.out.println("Error, debe ingresar una opcion valida");
            }
        }
    }
}
