package domain.PreguntasAdopcion;

import java.util.List;

public class RespuestaAdopcion {
    PreguntaAdopcion pregunta;
    List<String> valor;

    public void setPregunta(PreguntaAdopcion pregunta) {
        this.pregunta = pregunta;
    }

    public void agregarValor(String valor){
        this.valor.add(valor);
    }
    public void eliminarValor(String valor){
        this.valor.remove(valor);
    }
    public List<String> getValor() {
        return valor;
    }
}
