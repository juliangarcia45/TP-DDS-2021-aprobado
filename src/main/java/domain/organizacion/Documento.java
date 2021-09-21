package domain.organizacion;

import javax.persistence.*;

@Entity
@Table(name="documento")
public class Documento {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private float numero;

    @Column
    private String tipo;
}
