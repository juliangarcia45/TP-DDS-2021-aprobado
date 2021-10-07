package domain.persistencia;

import domain.BDUtils.EntityManagerHelper;
import domain.organizacion.PublicacionMascotaPerdida;
import domain.organizacion.Rescatista;
import domain.organizacion.Ubicacion;
import org.junit.Test;

public class persistiendoPubliMascPerdida {
    @Test
    public void persistir(){
        Rescatista raul= new Rescatista("raul","123456");
        Ubicacion puntoEncuentro= new Ubicacion();
        puntoEncuentro.setLatitud(500);
        puntoEncuentro.setLongitud(500);
        raul.setDireccionEncuentroMascota(puntoEncuentro);
        raul.setDescripcionMascota("azul");
        raul.setDireccionRescatista("calle falsa 123");
        PublicacionMascotaPerdida publicacion=new PublicacionMascotaPerdida(raul,null,"asa");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(raul);
        EntityManagerHelper.getEntityManager().persist(publicacion);
        EntityManagerHelper.commit();


    }


}
