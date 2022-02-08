package domain.entities.fotos;

import java.io.IOException;

public interface Estandarizador {
    void normalizarImagen(String path) throws IOException;
}
