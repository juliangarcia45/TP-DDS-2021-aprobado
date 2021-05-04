package domain;


import domain.autenticacion.Administrador;
import domain.autenticacion.Usuario;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class agregarCaracteristica {
    @Test
    public void agregarCaracteristica() throws FileNotFoundException {
        Administrador admin = new Administrador("admin","admin",null,null,null,null,null);
        admin.agregarCaracteristica("Caracteristica1", "Valor Caracteristica1");
        admin.agregarCaracteristica("Caracteristica2", "Valor Caracteristica2");
        admin.agregarCaracteristica("Caracteristica3", "Valor Caracteristica3");
        FileOutputStream organizacionConfig = new FileOutputStream("src\\main\\java\\domain\\autenticacion\\configOrganizacion.txt");
        admin.agregarMapAlFile(organizacionConfig);

        Map<String,String> mapLeido = admin.leerMapDelFile(organizacionConfig);

        assertEquals("Valor Caracteristica1",mapLeido.get("Caracteristica1"));
        System.out.println("Los values del map son: \n" + mapLeido.values());;

    }
}
