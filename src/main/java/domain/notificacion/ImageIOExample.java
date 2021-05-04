package domain.notificacion;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class ImageIOExample {

    public static void main( String[] args ){
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth) throws Exception {
        return Scalr.resize(originalImage, targetWidth);
    }

    public static void imageIoWrite(String tamanio, String formato, String path) {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(path);
            bImage = ImageIO.read(initialImage);
            ImageIO.write(bImage, formato, new File(path +"."+formato));
        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

}