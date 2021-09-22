package domain.organizacion;
import domain.autenticacion.Administrador;
import domain.entidadPersistente.EntidadPersistente;
import net.coobird.thumbnailator.Thumbnails;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Entity
@Table(name="organizacion")
public class Organizacion extends EntidadPersistente {

    @OneToMany(mappedBy = "organizacion",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Voluntario> voluntarios=new ArrayList<>();

    @OneToMany(mappedBy = "organizacion",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Administrador> administradores=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Publicacion> listaPublicaciones = new ArrayList<>();

    @OneToOne
    private Ubicacion ubicacion;

    @Column
    private Integer anchoFoto;

    @Column
    private Integer largoFoto;

    @Column
    private String formatoEstandar;

    public void agregarVoluntario(Voluntario voluntario){
        this.voluntarios.add(voluntario);
    }
    public void agregarAdmin(Administrador administrador){
        this.administradores.add(administrador);
    }




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


    public void normalizarFoto2(String path, String outputPath) throws IOException {
        Thumbnails.of(path)
                .size(this.getAnchoFoto(), this.getLargoFoto())
                .outputFormat(this.getFormatoEstandar())
                .toFile("assets/thumbnail."+this.getFormatoEstandar());
    }
    public void addPublicacion(Publicacion publicacion){
        this.listaPublicaciones.add(publicacion);
    }
    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }
    public List<Publicacion> publicacionesAprob(){
        return this.obtenerPublicacionesSegun(EstadoPublicacion.APROBADO);
    }
    
    public List<Publicacion> obtenerPublicacionesSegun(EstadoPublicacion estado ){
        return this.getListaPublicaciones().stream().filter(Publicacion -> Publicacion.getEstado()==estado).collect(Collectors.toList());
    }
    
    public List<Publicacion> publicacionesDeMascotasPerdidas(){
        return this.publicacionesAprob().stream().filter(publicacion -> publicacion instanceof PublicacionMascotaPerdida).collect(Collectors.toList());
    }
    public List<Publicacion> publicacionesDeMascotasEnAdopcion(){
        return this.publicacionesAprob().stream().filter(publicacion -> publicacion instanceof PublicacionMascotaEnAdopcion).collect(Collectors.toList());
    }


    public List<Administrador> getAdministradores() {
        return administradores;
    }
    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }
    public void setVoluntarios(List<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

  /*  public void agregarPublicacion(Publicacion publicacion){
        // deberia mandarle la publicacion a los voluntarios pero aca le mando solamente todas al primero de la lista...
        if (this.getVoluntarios().get(0).aprobarPublicacion(publicacion)) {
            listaPublicaciones.add(publicacion);
        }
    }
    public List<Voluntario> getVoluntarios(){
        return usuarios.stream().filter(usuario -> usuario instanceof Voluntario ).collect(Collectors.toList());
    }
*/
}
