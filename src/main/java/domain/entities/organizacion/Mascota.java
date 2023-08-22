package domain.entities.organizacion;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;


import java.io.File;

import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.entidadPersistente.EntidadPersistente;
import domain.entities.fotos.Foto;
import domain.entities.generadorQR.*;
import org.apache.commons.mail.EmailException;

import javax.persistence.*;

@Entity
@Table(name="mascota")
public class Mascota extends EntidadPersistente {

    @Column
    private String nombre;

    @Column
    private String sexo;

    @Column
    private String apodo;

    @ManyToOne
    @JoinColumn(name="duenio_id", referencedColumnName = "id")
    private Duenio duenio;

    @Transient
    private GeneradorQR qr;

    @Column
    private Integer edad;

    @Column
    private String descripcion;

    @Column
    private String especie;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_mascota", referencedColumnName = "id")
    private List<Foto> fotos=new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_mascota", referencedColumnName = "id")
    private List<RespuestaAdopcion> caracteristicas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "estado_mascota_id")
    private EstadoMascota estado;

    public Mascota(String nombre, String sexo, String apodo, String descripcion, int edad, String especie, List<Foto> fotos, List<RespuestaAdopcion> caracteristicas, Duenio duenio, EstadoMascota estado){
        this.nombre = nombre;
        this.sexo=sexo;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.edad = edad;
        this.especie=especie;
        this.fotos = fotos;
        this.caracteristicas= caracteristicas;
        this.duenio = duenio;
        this.estado = estado;
        this.qr = new GeneradorQR();
    }

    public Mascota(){}

    public void notificarDuenio(String mensaje){
        this.getDuenio().getMediosDeContacto().stream().forEach(contacto -> {
            try {
                contacto.notificar(mensaje);
            } catch (EmailException e) {
                e.printStackTrace();
            }
        });
    }


    public File generateQR() {
        File f = new File("qrCode.png");
        String text = this.duenio.getMediosDeContacto().get(0).getContacto();


        try {

            qr.generateQR(f, text, 300, 300);
            System.out.println("QRCode Generated: " + f.getAbsolutePath());

            String qrString = qr.decoder(f);
            System.out.println("Text QRCode: " + qrString);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    public void leerQr(File f){
        try{
            String qrString = qr.decoder(f);
            System.out.println("Text QRCode: " + qrString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RespuestaAdopcion> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<RespuestaAdopcion> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    public GeneradorQR getQr() {
        return qr;
    }

    public void setQr(GeneradorQR qr) {
        this.qr = qr;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}

