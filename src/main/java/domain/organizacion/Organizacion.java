package domain.organizacion;

import domain.autenticacion.Usuario;

import java.io.*;
import java.util.List;

public class Organizacion {
    private List<Usuario> usuarios;
    private int anchoFoto;
    private int largoFoto;
    private String formatoEstandar;

    public Organizacion(int anchoFoto,int largoFoto, String formatoEstandar){
        this.anchoFoto = anchoFoto;
        this.largoFoto = largoFoto;
        this.formatoEstandar = formatoEstandar;
    }


    public String getFormatoEstandar() {
        return formatoEstandar;
    }

    public void setFormatoEstandar(String formatoEstandar) {
        this.formatoEstandar = formatoEstandar;
    }

    public int getAnchoFoto() {
        return anchoFoto;
    }

    public void setAnchoFoto(int anchoFoto) {
        this.anchoFoto = anchoFoto;
    }

    public int getLargoFoto() {
        return largoFoto;
    }

    public void setLargoFoto(int largoFoto) {
        this.largoFoto = largoFoto;
    }
}
