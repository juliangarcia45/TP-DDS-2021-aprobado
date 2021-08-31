package domain;


import domain.autenticacion.Administrador;

import org.junit.Test;

import java.io.*;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class agregarCaracteristica {

    @Test
    public void agregarCaracteristicaTest() throws FileNotFoundException {
        Administrador admin = new Administrador("admin","admin");
        admin.agregarCaracteristica("Caracteristica1", "Valor Caracteristica1");
        admin.agregarCaracteristica("Caracteristica2", "Valor Caracteristica2");
        admin.agregarCaracteristica("Caracteristica3", "Valor Caracteristica3");
        admin.agregarCaracteristica("Caracteristica4", "Valor Caracteristica4");

        admin.agregarAlMapCaracteristicas();

        Map<String,String> mapLeido = admin.leerMapCaracteristicas();

        assertEquals("Valor Caracteristica1",mapLeido.get("Caracteristica1"));
        System.out.println("Los values del map son: \n" + mapLeido.values());;

    }
}
