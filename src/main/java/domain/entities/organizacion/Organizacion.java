package domain.entities.organizacion;
import domain.entities.PreguntasAdopcion.PreguntaAdopcion;
import domain.entities.autenticacion.Administrador;
import domain.entities.entidadPersistente.EntidadPersistente;
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

    @Column
    private String nombre;



    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="organizacion_id", referencedColumnName="id")
    private List<Publicacion> listaPublicaciones = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL)
    private Ubicacion ubicacion;

    @Column
    private String direccion;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="Organizacion_Preguntas")
    private List<PreguntaAdopcion> preguntas=new ArrayList<>();

    @Transient
    private Integer anchoFoto;

    @Transient
    private Integer largoFoto;

    @Transient
    private String formatoEstandar;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<Publicacion> publicacionesEnEspera(){
        return this.obtenerPublicacionesSegun(EstadoPublicacion.EN_ESPERA);
    }



    
    public List<Publicacion> obtenerPublicacionesSegun(EstadoPublicacion estado ){
        return this.getListaPublicaciones().stream().filter(Publicacion -> Publicacion.getEstado()==estado).collect(Collectors.toList());
    }
    
    public List<Publicacion> publicacionesDeMascotasPerdidasAprobadas(){
        return this.publicacionesAprob().stream().filter(publicacion -> publicacion instanceof PublicacionMascotaPerdida).collect(Collectors.toList());
    }

    public List<Publicacion> publicacionesDeMascotasPerdidasEnEspera(){
        return this.publicacionesEnEspera().stream().filter(publicacion -> publicacion instanceof PublicacionMascotaPerdida).collect(Collectors.toList());
    }

    public List<Publicacion> publicacionesDeMascotasEnAdopcionAprobadas(){
        return this.publicacionesAprob().stream().filter(publicacion -> publicacion instanceof PublicacionMascotaEnAdopcion).collect(Collectors.toList());
    }

    public List<Publicacion> publicacionesDeMascotasEnAdopcionEnEspera(){
        return this.publicacionesEnEspera().stream().filter(publicacion -> publicacion instanceof PublicacionMascotaEnAdopcion).collect(Collectors.toList());
    }


    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<PreguntaAdopcion> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaAdopcion> preguntas) {
        this.preguntas = preguntas;
    }

    public Organizacion() {
    }

}
