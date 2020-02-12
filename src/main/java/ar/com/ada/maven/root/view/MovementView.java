package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Movement;
import ar.com.ada.maven.root.model.dto.Movement_type;
import ar.com.ada.maven.root.utils.Singletone;

import java.util.Date;
import java.util.List;

public class MovementView {

    public void printAllMovement(List<Movement> movementList) {

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

    public Integer printMenuTypeMovement(Movement_type newTypeMovement) {
        System.out.println("Seleccione la operación que desea realizar");
        System.out.println("| 1 | Debito");
        System.out.println("| 2 | Credito");
        System.out.println("| 3 | Salir");

        //Este metodo sirve para que capture y valide la entrada de datos del usuario en formato numerico
        String option = Singletone.getInputInteger();
        // y en return convierte string numerico a entero(Integer)
        return Integer.valueOf(option);

    }

    public void showNewMovement(Movement newMovement) {
        System.out.println("Se agrega un nuevo movimiento");
        System.out.println(newMovement.getId() + " " + newMovement.getAmount() + " " + newMovement.getDate().toString() + " "
                + newMovement.getDescription() + " " + newMovement.getMovement_type());
    }
}
