package domain.entities.organizacion;

import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name="ubicacion")
public class Ubicacion extends EntidadPersistente {

    @Column
    private double latitud;
    @Column
    private double longitud;

    public double distanciaCoord(Ubicacion ubicacion) {
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(ubicacion.getLatitud() - this.getLatitud() );
        double dLng = Math.toRadians(ubicacion.getLongitud() - this.getLongitud());
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(this.getLatitud())) * Math.cos(Math.toRadians(ubicacion.getLatitud()));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }
    public Ubicacion() {
    }

    public int distanciaMasCortaA(List<Ubicacion> ubicaciones){
        List<Double> distancias= ubicaciones.stream().map(Ubicacion -> Ubicacion.distanciaCoord(this)).collect(Collectors.toList());
        return distancias.indexOf(Collections.min(distancias));
    }



    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
