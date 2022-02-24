package domain.entities.organizacion;

import java.util.Map;
import java.util.List;


import java.io.File;

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
    private boolean sexo;

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
    private boolean especie;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_mascota", referencedColumnName = "id")
    private List<Foto> fotos;

    @ElementCollection
    private Map<String,String> caracteristicas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "estado_mascota_id")
    private EstadoMascota estado;

    public Mascota(String nombre, String sexo, String apodo, String descripcion, int edad, String especie, List<Foto> fotos, Map<String,String> caracteristicas, Duenio duenio, EstadoMascota estado){
        this.nombre = nombre;
        this.setSexo(sexo);
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.edad = edad;
        this.setEspecie(especie);
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

    public void setSexo(String sexo) {
        switch (sexo) {
            case "Hembra":
                this.sexo = false;
                break;
            case "Macho":
                this.sexo = true;
                break;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    public boolean isSexo() {
        return sexo;
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

    public boolean isEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        switch (especie){
            case "Perro":
                this.especie= false;
                break;
            case "Gato":
                this.especie=true;
                break;
        }
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFoto(Foto foto) {
        fotos.add(foto);
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Map<String, String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    /*private Mascota(MascotaBuilder builder) {
        this.nombre = builder.nombre;
        this.sexo = builder.sexo;
        this.apodo = builder.apodo;
        this.descripcion = builder.descripcion;
        this.edad = builder.edad;
        this.especie = builder.especie;
        this.fotos = builder.fotos;
        this.caracteristicas= builder.caracteristicas;
        this.idMascota=  builder.idMascota;
        this.duenio = builder.duenio;
        this.estado = builder.estado;
        this.qr = new GeneradorQR();
    }
    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    public void setEstado(EstadoMascota estado){
        this.estado = estado;
    }


    public String getDescripcion() {
        return descripcion;
    }
   
   
    public List<String> getFotos() {
        return fotos;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public String getNombre() {
        return nombre;
    }




    public static class MascotaBuilder{
        private String nombre;
        private Integer idMascota;
        private boolean sexo;
        private String apodo;
        private String descripcion;
        private boolean especie;
        private List<String> fotos;
        private Map<String,String> caracteristicas;
        private Integer edad;
        private Duenio duenio;
        private EstadoMascota estado;
        
        public MascotaBuilder(List<String> fotos , String descripcion){
            this.descripcion = descripcion;
            this.fotos = fotos;
        }

        public MascotaBuilder nombre(String name){
            this.nombre = name;
            return this;
        }

        public MascotaBuilder idMascota(Integer idMascota){
            this.idMascota= idMascota;
            return this;
        }

        public MascotaBuilder sexo(String sexo){
            switch (sexo){
                case "Hembra":
                    this.especie= false;
                    break;
                case "Macho":
                    this.especie=true;
                    break;
            }
            return this;
        }
        public MascotaBuilder apodo(String apodo){
            this.nombre = apodo;
            return this;
        }
        public MascotaBuilder especie(String especie){
            switch (especie){
                case "Perro":
                    this.especie= false;
                    break;
                case "Gato":
                    this.especie=true;
                    break;
            }
            return this;
        }
        public MascotaBuilder caracteristicas(Map<String,String> caracteristicas){
            this.caracteristicas = caracteristicas;
            return this;
        }
        public MascotaBuilder edad(Integer edad){
            this.edad= edad;
            return this;
        }
        public MascotaBuilder fotos(List<String> fotos){
            this.fotos= fotos;
            return this;
        }
        public MascotaBuilder duenio(Duenio  duenio){
            this.duenio =duenio;
            return this;
        }
        public MascotaBuilder estado(EstadoMascota estado){
            this.estado =estado;
            return this;
        }

        public Mascota build(){
            return new Mascota(this);
        }

        public MascotaBuilder() {
        }

    }*/
}

