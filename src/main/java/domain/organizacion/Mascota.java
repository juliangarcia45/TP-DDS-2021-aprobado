package domain.organizacion;

import java.util.Map;
import java.awt.*;
import java.util.List;


import java.io.File;
import domain.generadorQR.*;
public class Mascota {
    private final String nombre;
    private final Integer idMascota;
    private final boolean sexo;
    private final String apodo;
    private Duenio duenio;
    private GeneradorQR qr;
    private final Integer edad;
    private final String descripcion;
    private final boolean especie;
    private final List<String> fotos;
    private final Map<String,String> caracteristicas;
    private EstadoMascota estado;
   
    private Mascota(MascotaBuilder builder) {
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

    public void notificarDuenio(String mensaje){
        this.getDuenio().getMediosDeContacto().stream().forEach(contacto -> contacto.notificar(mensaje));
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

        public MascotaBuilder sexo(Boolean sexo){
            this.sexo = sexo;
            return this;
        }
        public MascotaBuilder apodo(String apodo){
            this.nombre = apodo;
            return this;
        }
        public MascotaBuilder especie(Boolean especie){
            this.sexo = especie;
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
    }

}

