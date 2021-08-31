package domain;

import domain.autenticacion.Usuario;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;
import org.junit.Test;

import domain.autenticacion.validadorDeContrasenias.ValidadorContrasenias;

import static org.junit.Assert.*;

public class ValidadorContraseniasTest {
        @Test
        public void validarContrasenias() {
            Duenio jorge= new Duenio("jorge", "jorge");
            Mascota mascota = new Mascota("gato",true,null,null,null,true,null,jorge,null);
            jorge.registrarMascota(mascota);

            assertEquals(mascota,jorge.getMascotas().get(0));
            assertFalse(ValidadorContrasenias.getInstance().validar("juan"));
            assertTrue(ValidadorContrasenias.getInstance().validar("dgdvcdgrgcvgdr"));
            assertTrue(ValidadorContrasenias.getInstance().validar("platense10"));
        }
    }

