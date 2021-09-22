package domain.organizacion;

import domain.entidadPersistente.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="documento")
public class Documento extends EntidadPersistente {

    @Column
    private float numero;

    @Column
    private String tipo;
}
