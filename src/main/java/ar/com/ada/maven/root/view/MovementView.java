package ar.com.ada.maven.root.view;

import ar.com.ada.maven.root.model.dto.Account;
import ar.com.ada.maven.root.model.dto.Movement;
import ar.com.ada.maven.root.model.dto.MovementType;
import ar.com.ada.maven.root.utils.Singletone;

import java.util.Date;
import java.util.HashMap;
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
            MovementType movementMovement_type = movement.getMovement_type();
            System.out.println(" id: " + movementId + " Fecha: " + movementDate + " Importe: " + movementAmount +
                    " Descripcion: " + movementDescription + " Cuenta: " + movementAccount + " Tipo de Movimiento: "
                    + movementMovement_type);
        });
    }

    public String printMenuTypeMovement() {
        System.out.println("Seleccione la operación que desea realizar");
        System.out.println("| 1 | Debito");
        System.out.println("| 2 | Credito");
        System.out.println("| 3 | Salir");

        //Este metodo sirve para que capture y valide la entrada de datos del usuario en formato numerico
        String option = Singletone.getInputInteger();
        // y en return convierte string numerico a entero(Integer)
        return String.valueOf(option);

    }

    public HashMap<String, String> getNewMovementData() {
        HashMap<String, String> data = new HashMap<>();
        //agregar lo que se necesita para crear un nuevo movimiento
        System.out.println("ingrese el monto, por favor: ");
        String amount = Singletone.getInputInteger();
        data.put("amount", amount);
        System.out.println("ingrese la descripción, por favor: ");
        String description = Singletone.getInputString();
        data.put("description", description);

        return data;

    }

    public void showNewMovement(Movement newMovement) {
        System.out.println("Se agrega un nuevo movimiento");
        System.out.println(newMovement.getId() + " " + newMovement.getAmount() + " " + newMovement.getDate().toString() + " "
                + newMovement.getDescription() + " " + newMovement.getMovement_type());
    }

    public void createMovementCanceled() {
        System.out.println("Se ha cancelado el proceso de elegir un tipo de movimiento");

    }

    public void creationLimitNotPermitted() {
        System.out.println("El Movimiento que intentas crear no esta permitido");
    }


}
