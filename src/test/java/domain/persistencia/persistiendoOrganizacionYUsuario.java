package domain.persistencia;

import bd.BDUtils.EntityManagerHelper;
import domain.entities.PreguntasAdopcion.Opcion;
import domain.entities.PreguntasAdopcion.PreguntaAdopcion;
import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.PreguntasAdopcion.TipoPregunta;
import domain.entities.autenticacion.Administrador;
import domain.entities.notificacion.Contacto;
import domain.entities.notificacion.MedioDeNotificacion;
import domain.entities.organizacion.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class persistiendoOrganizacionYUsuario {
    @Test
    public void persistir(){
        //Duenio
        Duenio raul= new Duenio("raul","123456");
        List<Contacto> contactosRaul=new ArrayList<>();
        MedioDeNotificacion medioRaul=new MedioDeNotificacion();
        List<MedioDeNotificacion> listaMedios=new ArrayList<>();
        listaMedios.add(medioRaul);
        Contacto contactoRaul=new Contacto("Raul","Gonzalez","raul@gmail.com","49751235",listaMedios);
        contactosRaul.add(contactoRaul);
        Documento documentoRaul= new Documento(123456,"DNI");
        Mascota calli=new Mascota();
        calli.setDescripcion("a");
        calli.setApodo("calli");
        calli.setEdad(14);
        raul.setNombre("Raul");
        raul.setApellido("Gonzalez");
        raul.setDocumento(documentoRaul);
        raul.registrarMascota(calli);
        raul.setMediosDeContacto(contactosRaul);

        //Organizacion
        Organizacion organizacion=new Organizacion();
        Voluntario vol=new Voluntario("vol","123456");
        Administrador admin=new Administrador("admin","123456");
        vol.setOrganizacion(organizacion);
        Ubicacion lugarOrg= new Ubicacion();
        lugarOrg.setLatitud(500);
        lugarOrg.setLongitud(500);
        organizacion.setUbicacion(lugarOrg);
        organizacion.setDireccion("Calle falsa 123");
        organizacion.setNombre("asdas");
        //Publicacion
        PublicacionMascotaEnAdopcion publi=new PublicacionMascotaEnAdopcion(raul,calli);

        //Pregunta y respuesta
        PreguntaAdopcion pregunta=new PreguntaAdopcion("??", TipoPregunta.SINGLE);
        pregunta.getOpciones().add(new Opcion("as",pregunta));
        organizacion.getPreguntas().add(pregunta);
        RespuestaAdopcion respuesta=new RespuestaAdopcion(pregunta,new ArrayList<>());
        respuesta.getOpciones().add(new Opcion("a",pregunta));
        publi.agregarRespuesta(respuesta);
        organizacion.addPublicacion(publi);
        publi.aprobarPublicacion();

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(raul);
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();

    }
}
