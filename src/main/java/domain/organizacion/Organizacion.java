package domain.organizacion;
import domain.autenticacion.Usuario;
<<<<<<< HEAD
=======
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
>>>>>>> main
import java.util.List;

public class Organizacion {
    private List<Usuario> usuarios;
    private int anchoFoto;
    private int largoFoto;
    private String formatoEstandar;

<<<<<<< HEAD
    public Organizacion(int anchoFoto,int largoFoto, String formatoEstandar){
=======
    public void definirTamanioYFormatoEstandar(int anchoFoto,int largoFoto, String formatoEstandar){
>>>>>>> main
        this.anchoFoto = anchoFoto;
        this.largoFoto = largoFoto;
        this.formatoEstandar = formatoEstandar;
    }
<<<<<<< HEAD


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
=======
    private void normalizarFoto(Mascota mascota, String path){
        int anchoFoto = this.getAnchoFoto();
        int largoFoto = this.getLargoFoto();
        String formato = this.getFormatoEstandar();
        try
        {
            ImageIcon ii = new ImageIcon(path);
            BufferedImage bImage = new BufferedImage(anchoFoto, largoFoto, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bImage.createGraphics();
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
    public String getFormatoEstandar() {
        return formatoEstandar;
    }
    public int getAnchoFoto() { return anchoFoto; }
    public int getLargoFoto() { return largoFoto; }
}
>>>>>>> main
