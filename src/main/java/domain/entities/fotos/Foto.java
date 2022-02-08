package domain.entities.fotos;

import config.Config;
import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "fotos")
public class Foto extends EntidadPersistente {
    @Transient
    private static Estandarizador normalizador = new Graphics2DResizer();

    @Column(name = "url")
    private String url;

    @Transient
    private static final Integer width = Config.WIDTH_DEFINIDO;
    @Transient
    private static final Integer height = Config.HEIGHT_DEFINIDO;

    public Foto(String nombre) throws IOException {
        this.url = nombre;
    }
    public Foto(){}

    public void normalizar() throws IOException {
        normalizador.normalizarImagen(this.url);
    }

    private String generarNombre(File file){
        String name = file.getName();
        String datetime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        return datetime + name;
    }

    public String getUrl() {
        return Config.RUTA_IMAGENES_LEER + this.url;
    }
}
