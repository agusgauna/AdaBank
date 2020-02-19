package ar.com.ada.maven.root.utils;

public class IbanGenerator {

    public static String Generation(String countryCode, Integer codeBank, Integer branchCode, Integer accounType, Integer controlNumber){
        //TODO: CONCATENAR LOS CODIGOS Y ARMAR FORMATO IBAN
        String iban = "";
        iban += countryCode;
        iban += "21";
        iban += codeBank.toString(); // rellenar el entero en 4 posiciones
        iban += branchCode.toString(); // rellenar el entero en 4 posiciones

        iban += accounType.toString(); // rellenar el entero en 2 lugares
        iban += controlNumber.toString(); // rellenar el entero en 10 posiciones

        return iban;

    };

}
