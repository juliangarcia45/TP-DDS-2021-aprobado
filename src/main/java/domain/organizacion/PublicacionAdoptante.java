package domain.organizacion;

import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PublicacionAdoptante{
    private Duenio interesado;
    private List<RespuestaAdopcion> respuestasPreferencias=new ArrayList<>();

    public PublicacionAdoptante(Duenio interesado, List<RespuestaAdopcion> respuestasPreferencias) {
        this.interesado = interesado;
        this.respuestasPreferencias=respuestasPreferencias;
    }

    public void agregarRespuesta(RespuestaAdopcion respuesta) {
        this.respuestasPreferencias.add(respuesta);
    }

    public void eliminarRespuesta(RespuestaAdopcion respuesta) {
        this.respuestasPreferencias.remove(respuesta);
    }

    public List<RespuestaAdopcion> getRespuestas() {
        return respuestasPreferencias;
    }

    public Duenio getInteresado() {
        return interesado;
    }
    public void setInteresado(Duenio interesado) {
        this.interesado = interesado;
    }





}
