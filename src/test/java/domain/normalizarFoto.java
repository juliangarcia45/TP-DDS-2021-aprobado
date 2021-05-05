package domain;


import domain.organizacion.Mascota;
import domain.organizacion.Organizacion;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


import java.util.ArrayList;
import java.util.List;



public class normalizarFoto {
   /* Organizacion organizacion;
    BufferedImage bImage = null;

    @Test
    public void normalizarFoto() throws IOException {

        organizacion = new Organizacion("300", "png");
        String tamanioEstandar = organizacion.getTamanioEstandar();
        String formato = organizacion.getFormatoEstandar();
        List<Image> fotosMascota = new ArrayList<Image>();
        String path = "assets\\image.jpg";
        imageIoWrite(tamanioEstandar, formato, path);
        fotosMascota.add(bImage);
        Mascota mascota = new Mascota("tuli", true, null, null, null, true, fotosMascota, null,null);
        System.out.println(fotosMascota);
    }

    private void imageIoWrite(String tamanio, String formato, String path) {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(path);
            bImage = ImageIO.read(initialImage);
            ImageIO.write(bImage, formato, new File("assets\\image."+formato));
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }*/
}

