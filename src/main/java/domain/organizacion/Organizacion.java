package domain.organizacion;
import domain.autenticacion.Administrador;
import domain.autenticacion.Usuario;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Organizacion {
    private List<Usuario> usuarios;
    private Integer anchoFoto;
    private Integer largoFoto;
    private String formatoEstandar;
    private RegistroDeUsuarios registroDeUsuarios;

    public void definirTamanioYFormatoEstandar() throws IOException {
        InputStream ip = Organizacion.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(ip);
        this.anchoFoto = Integer.parseInt(properties.getProperty("widthImage"));
        this.largoFoto = Integer.parseInt(properties.getProperty("heightImage"));
        this.formatoEstandar = properties.getProperty("formatImage");
    }
    public String getFormatoEstandar() {
        return formatoEstandar;
    }
    public Integer getAnchoFoto() { return anchoFoto; }
    public Integer getLargoFoto() { return largoFoto; }
    public void registrarUsuario(Usuario usuario){
        Usuario registrado = this.registroDeUsuarios.registrar(usuario);
        this.usuarios.add(registrado);
    }

    /*public void normalizarFoto(String path, String outputPath) throws IOException {
        File destinationDir = new File(outputPath);
        Thumbnails.of(path)
                .size(this.getAnchoFoto(), this.getLargoFoto())
                .outputFormat(this.getFormatoEstandar())
                .toFiles(destinationDir, Rename.PREFIX_HYPHEN_THUMBNAIL);
    }
     */
    public void normalizarFoto2(String path, String outputPath) throws IOException {
        Thumbnails.of(path)
                .size(this.getAnchoFoto(), this.getLargoFoto())
                .outputFormat(this.getFormatoEstandar())
                .toFile("assets/thumbnail."+this.getFormatoEstandar());
    }

}
