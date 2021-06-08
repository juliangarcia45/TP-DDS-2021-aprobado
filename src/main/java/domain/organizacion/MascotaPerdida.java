package domain.organizacion;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class MascotaPerdida extends Mascota{
    public MascotaPerdida(String nombre, boolean sexo, String apodo, String descripcion, Integer edad, boolean especie, List<Image> fotos, Duenio duenio, Map<String, String> caracteristicas) {
        super(nombre, sexo, apodo, descripcion, edad, especie, fotos, duenio, caracteristicas);
    }
}
