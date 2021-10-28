package domain;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.Mascota;
import org.junit.Test;

import domain.entities.autenticacion.validadorDeContrasenias.ValidadorContrasenias;

import static org.junit.Assert.*;

public class ValidadorContraseniasTest {
        @Test
        public void validarContrasenias() {
            Duenio jorge= new Duenio("jorge", "jorge");
            Mascota mascota = new Mascota.MascotaBuilder(null,null).nombre("gato").build();
            mascota.setDuenio(jorge);
            jorge.registrarMascota(mascota);

            assertEquals(mascota,jorge.getMascotas().get(0));
            assertFalse(ValidadorContrasenias.getInstance().validar("juan"));
            assertTrue(ValidadorContrasenias.getInstance().validar("dgdvcdgrgcvgdr"));
            assertTrue(ValidadorContrasenias.getInstance().validar("platense10"));
        }
    }

