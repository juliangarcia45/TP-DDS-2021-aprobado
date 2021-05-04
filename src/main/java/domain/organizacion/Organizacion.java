package domain.organizacion;

import domain.autenticacion.Usuario;

import java.io.*;
import java.util.List;

public class Organizacion {
    private List<Usuario> usuarios;
    private String tamanioEstandar;
    private String formatoEstandar;

    public Organizacion(String tamanioEstandar, String formatoEstandar){
        this.tamanioEstandar = tamanioEstandar;
        this.formatoEstandar = formatoEstandar;
    }

    public String getTamanioEstandar() {
        return tamanioEstandar;
    }

    public void setTamanioEstandar(String tamanioEstandar) {
        this.tamanioEstandar = tamanioEstandar;
    }

    public String getFormatoEstandar() {
        return formatoEstandar;
    }

    public void setFormatoEstandar(String formatoEstandar) {
        this.formatoEstandar = formatoEstandar;
    }

    public Organizacion() {
    }
}
