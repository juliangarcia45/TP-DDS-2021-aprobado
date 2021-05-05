package domain;


import domain.organizacion.Mascota;
import domain.organizacion.Organizacion;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


import java.util.ArrayList;
import java.util.List;


/*
public class normalizarFoto {
    Organizacion organizacion= new Organizacion(300,300, "png");

    @Test
    public void normalizar() throws IOException {

        List<Image> fotosMascota = new ArrayList<Image>();
        String path = "assets\\image.jpg";
        redimensionarImagen(largoFoto,anchoFoto, formato, path);
        fotosMascota.add(bImage);
        Mascota mascota = new Mascota("tuli", true, null, null, null, true, fotosMascota, null,null);
        System.out.println(fotosMascota);
    }

    private void redimensionarImagen(Organizacion organizacion,Mascota mascota, String path){
        int anchoFoto = organizacion.getAnchoFoto();
        int largoFoto = organizacion.getLargoFoto();
        String formato = organizacion.getFormatoEstandar();
        try
        {
            ImageIcon ii = new ImageIcon(path);
            BufferedImage bImage = new BufferedImage(anchoFoto, largoFoto, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D)bImage.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY));
            boolean b = g2d.drawImage(ii.getImage(), 0, 0, largoFoto, anchoFoto, null);
            System.out.println(b);
            ImageIO.write(bImage, formato, new File(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
    /*private void imageIoWrite(String tamanio, String formato, String path) {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(path);
            bImage = ImageIO.read(initialImage);
            ImageIO.write(bImage, formato, new File("assets\\image."+formato));
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }     */
