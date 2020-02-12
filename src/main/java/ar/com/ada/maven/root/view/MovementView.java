package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Movement;
import ar.com.ada.maven.root.model.dto.Movement_type;
import ar.com.ada.maven.root.utils.Singletone;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovementView {

    public void printAllMovement(final List<Movement> movementList) {

        System.out.printf(" El listado de Movimientos");

        movementList.stream().forEach(movement -> {
            Integer movementId = movement.getId();
            Date movementDate = movement.getDate();
            Double movementAmount = movement.getAmount();
            String movementDescription = movement.getDescription();
            Account movementAccount = movement.getAccount();
            Movement_type movementMovement_type = movement.getMovement_type();
            System.out.println(" id: " + movementId + " Fecha: " + movementDate + " Importe: " + movementAmount +
                    " Descripcion: " + movementDescription + " Cuenta: " + movementAccount + " Tipo de Movimiento: "
                    + movementMovement_type);
        });
    }

    public String printNewTypeMovement(Movement_type newTypeMovement ){
        System.out.println("Selecione la operacion que desea realizar");
        System.out.println("1. Debito");
        System.out.println("2. Credito");
        System.out.println("3. Salir");

        Scanner keyboard = Singletone.getInstance();
                while (true) {
                    try {
                        System.out.println("?");
                        String choice = keyboard.next(toString());
                        return choice;
                    } catch (InputMismatchException e) {
                        System.out.println("Es valida la eleccion");
                        keyboard.next();
                    }
            }
        }

    public void showNewMovement(Movement_type newMovement) {
        System.out.println("Se agrega un nuevo movement");
    }

}
