package domain.organizacion;
import domain.autenticacion.Usuario;
import net.coobird.thumbnailator.Thumbnails;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Organizacion {
    private List<Usuario> usuarios;
    private List<Publicacion> listaPublicaciones;
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
    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }
    public void registrarUsuario(Usuario usuario){
        Usuario registrado = this.registroDeUsuarios.registrar(usuario);
        this.usuarios.add(registrado);
    }

    public void normalizarFoto2(String path, String outputPath) throws IOException {
        Thumbnails.of(path)
                .size(this.getAnchoFoto(), this.getLargoFoto())
                .outputFormat(this.getFormatoEstandar())
                .toFile("assets/thumbnail."+this.getFormatoEstandar());
    }
    public void agregarPublicacion(Publicacion publicacion){
        // deberia mandarle la publicacion a los voluntarios pero aca le mando solamente todas al primero de la lista...
        if (this.getVoluntarios().get(0).aprobarPublicacion(publicacion)) {
            listaPublicaciones.add(publicacion);
        }
    }
    public List<Voluntario> getVoluntarios(){
        return usuarios.stream().filter(usuario -> /*usuario = Voluntario*/ ).collect(Collectors.toList());
    }

}
