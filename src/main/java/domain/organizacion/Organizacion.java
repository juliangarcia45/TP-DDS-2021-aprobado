package domain.organizacion;

import domain.autenticacion.Usuario;

import java.io.*;
import java.util.List;

public class Organizacion {
    private List<Usuario> usuarios;
    FileOutputStream fileOut = new FileOutputStream("/organizacionConfig");

    public Organizacion() throws FileNotFoundException {
    }
}
