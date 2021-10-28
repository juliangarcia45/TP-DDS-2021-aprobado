package domain.entities.organizacion;

import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="documento")
public class Documento extends EntidadPersistente {

    @Column
    private float numero;

    @Column
    private String tipo;


    public Documento(float numero, String tipo) {
        this.numero=numero;
        this.tipo=tipo;
    }
    public Documento() {
    }
}
