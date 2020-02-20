package ar.com.ada.maven.root.controller;

import ar.com.ada.maven.root.model.dao.Account_typeDAO;
import ar.com.ada.maven.root.model.dao.MovementDAO;
import ar.com.ada.maven.root.model.dao.MovementTypeDAO;
import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Account_type;
import ar.com.ada.maven.root.model.dto.Movement;
import ar.com.ada.maven.root.model.dto.MovementType;
import ar.com.ada.maven.root.view.MovementView;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MovementController {
    private static MovementView view = new MovementView();
    private static MovementDAO movementDAO = new MovementDAO();
    private static MovementTypeDAO movementTypeDAO = new MovementTypeDAO();


    public static void init() {
        boolean mm = false;
        while (!mm) {
            String option = view.printMenuTypeMovement();
            switch (option) {
                case "1":
                    MovementController.movementList();
                    break;
                default:
                    System.out.printf("Debe seleccionar una opción valida");
            }
        }
    }

    private static void movementList() {
        List<Movement> list = movementDAO.findAll();
        view.printAllMovement(list);
    }

    public static void createNewMovement(Account account) {
        HashMap<String, String> data = view.getNewMovementData();
        String typeMovementChoise = view.printMenuTypeMovement();
        //tengo que decirle a romi que invoque este metodo " createNewMovement " desde controller Account
        // cuando el usuario quiero agregar el movimiento


        if (typeMovementChoise.equals("3")) {
            view.createMovementCanceled();
        } else {
            MovementType movementType = null;
            boolean tm = false;
            while (!tm) {
                switch (typeMovementChoise) {
                    case "1":
                        movementType = movementTypeDAO.findByType("Debito");
                        break;
                    case "2":
                        movementType = movementTypeDAO.findByType("Credito");
                        break;
                    default:
                        System.out.printf("Debe seleccionar una opción valida");
                }
            }

            Date now = Date.from(Instant.now());
            Double amount = Double.parseDouble(data.get("amount"));
// se crea este if para que se pueda validar el if que hice del descubierto, setiando solo los datos
            if (hashContinueMovement(account.getAccount_type(), amount)) {

                Movement movement = new Movement(now, amount, data.get("description"), account, movementType);

                view.showNewMovement(movement);
            } else view.creationLimitNotPermitted();
        }


        // si un movimiento genera un descubierto mayor a 1000 para cuenta en pesos se debe rechazar
        //un descubierto mayor a 300 dolares se debe rechazar
        //un descubierto de 150 euros se debera rechazar
    }

    private static Boolean hashContinueMovement(Account_type accountType, Double amount) {
        int limitAmount = 0;
        switch (accountType.getCode_control()) {

            case 10:
                limitAmount = 1000;
                break;
            case 20:
                limitAmount = 300;
                break;
            case 30:
                limitAmount = 150;
                break;

        }
        if (amount > limitAmount)
            return false;
        else
            return true;
    }

}
