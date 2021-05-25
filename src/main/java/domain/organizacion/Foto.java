package domain.organizacion;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Foto {
    private String formato;
    private int anchofoto;
    private int largofoto;

    public void normalizar(Organizacion organizacion, Mascota mascota) throws IOException {
        String path = "assets\\image.jpg";
        redimensionarImagen(organizacion,mascota, path);
        System.out.println(mascota.getFotos());
    }

    private void redimensionarImagen(Organizacion organizacion, Mascota mascota, String path){
        int anchoFoto = organizacion.getAnchoFoto();
        int largoFoto = organizacion.getLargoFoto();
        String formato = organizacion.getFormatoEstandar();
        List<Image> fotosMascota = new ArrayList<Image>();
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
            mascota.getFotos().add(bImage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
