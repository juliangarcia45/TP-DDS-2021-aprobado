package domain.entities.autenticacion.validadorDeContrasenias;

import java.util.Arrays;
import java.util.List;

public class ValidadorContrasenias {
        private static final ValidadorContrasenias instance = new ValidadorContrasenias();
        private static Requisito r[]=	new Requisito []{
                new ContraseniasInvalidas(),
                new CaracteresConsecutivos(),
                new CaracteresRepetidos(),
                new CaracteresValidos(),
                new LongitudValida(),
        };

        static List<Requisito> requisitos = Arrays.asList(r);
        private ValidadorContrasenias() {
        }

        public static ValidadorContrasenias getInstance() {
            return instance;
        }


        static void agregarRequisitos() {
            ContraseniasInvalidas arcContInv = new ContraseniasInvalidas();
            CaracteresConsecutivos carCon = new CaracteresConsecutivos();
            CaracteresRepetidos carRep = new CaracteresRepetidos();
            CaracteresValidos carVal = new CaracteresValidos();
            LongitudValida longVal = new LongitudValida();

            requisitos.add(arcContInv);
            requisitos.add(carCon);
            requisitos.add(carRep);
            requisitos.add(carVal);
            requisitos.add(longVal);
        }

        public boolean validar(String contrasenia) {

            return requisitos.stream().allMatch(requisito -> requisito.validar(contrasenia));

        }

        public static void imprimirResultadoValidacion(String contrasenia) {
            String mensaje = "Contraseña invalida\n";
            ContraseniasInvalidas arcContInv = new ContraseniasInvalidas();
            CaracteresConsecutivos carCon = new CaracteresConsecutivos();
            CaracteresRepetidos carRep = new CaracteresRepetidos();
            CaracteresValidos carVal = new CaracteresValidos();
            LongitudValida longVal = new LongitudValida();
            if (!longVal.validar(contrasenia))
                mensaje += "Su contraseña es demasiado corta\n";
            if (!carVal.validar(contrasenia))
                mensaje += "Utilice caracteres validos\n";
            if (!arcContInv.validar(contrasenia))
                mensaje += "Su contraseña es demasiado frecuente\n ";
            if (!carRep.validar(contrasenia))
                mensaje += "Su contraseña tiene muchos caracteres repetidos\n ";
            if (!carCon.validar(contrasenia))
                mensaje += "Su contraseña tiene muchos caracteres consecutivos\n";
            if (longVal.validar(contrasenia) && carVal.validar(contrasenia) && arcContInv.validar(contrasenia)
                    && carRep.validar(contrasenia))
                mensaje = "Contraseña Valida";
            System.out.println(mensaje);
        }
}

