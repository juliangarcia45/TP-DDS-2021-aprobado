package domain.entities.hogaresAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HogarDeTransito {
    @SerializedName("id")
    private String id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("ubicacion")
    private Ubicacion ubicacion;

    private static class Ubicacion{
        @SerializedName("direccion")
        private String direccion;
        @SerializedName("lat")
        private Float lat;
        @SerializedName("lon")
        private Float longitud;
    }
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("admisiones")
    private Admisiones admisiones;
    private class Admisiones{
        @SerializedName("perros")
        private Boolean perros;
        @SerializedName("gatos")
        private Boolean gatos;
    }
    @SerializedName("capacidad")
    private Integer capacidad;
    @SerializedName("lugares_disponibles")
    private Integer lugares_disponibles;
    @SerializedName("patio")
    private Boolean patio;
    @SerializedName("caracteristicas")
    private ArrayList<String> caracteristicas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Admisiones getAdmisiones() {
        return admisiones;
    }

    public void setAdmisiones(Admisiones admisiones) {
        this.admisiones = admisiones;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getLugares_disponibles() {
        return lugares_disponibles;
    }

    public void setLugares_disponibles(Integer lugares_disponibles) {
        this.lugares_disponibles = lugares_disponibles;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public ArrayList<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
