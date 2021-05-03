package domain;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import domain.autenticacion.validadorDeContrasenias.ValidadorContrasenias;

    public class ValidadorContraseniasTest {
        @Test
        public void validarContrasenias() {

            assertFalse(ValidadorContrasenias.getInstance().validar("juan"));
            assertTrue(ValidadorContrasenias.getInstance().validar("dgdvcdgrgcvgdr"));
            assertTrue(ValidadorContrasenias.getInstance().validar("platense10"));
        }
    }

