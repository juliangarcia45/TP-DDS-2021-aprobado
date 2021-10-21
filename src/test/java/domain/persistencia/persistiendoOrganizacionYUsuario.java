package domain.persistencia;

import domain.BDUtils.EntityManagerHelper;
import domain.PreguntasAdopcion.PreguntaAdopcion;
import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.PreguntasAdopcion.TipoPregunta;
import domain.autenticacion.Administrador;
import domain.notificacion.Contacto;
import domain.notificacion.MedioDeNotificacion;
import domain.organizacion.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Year;
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
        Contacto contactoRaul=new Contacto("Raul","Gonzalez","raul@gmail.com",49751235,listaMedios);
        medioRaul.setContactoANotificar(contactoRaul);
        contactosRaul.add(contactoRaul);
        Documento documentoRaul= new Documento(123456,"DNI");
        Mascota calli=new Mascota.MascotaBuilder(null,"a").apodo("calli").edad(14).build();
        raul.setNombre("Raul");
        raul.setApellido("Gonzalez");
        raul.setDocumento(documentoRaul);
        raul.registrarMascota(calli);
        raul.setMediosDeContacto(contactosRaul);

        //Organizacion
        Organizacion organizacion=new Organizacion();
        Voluntario vol=new Voluntario("vol","123456");
        Administrador admin=new Administrador("admin","123456");
        organizacion.agregarAdmin(admin);
        organizacion.agregarVoluntario(vol);
        Ubicacion lugarOrg= new Ubicacion();
        lugarOrg.setLatitud(500);
        lugarOrg.setLongitud(500);
        organizacion.setUbicacion(lugarOrg);
        //Publicacion
        PublicacionMascotaEnAdopcion publi=new PublicacionMascotaEnAdopcion(raul,calli);

        //Pregunta y respuesta
        PreguntaAdopcion pregunta=new PreguntaAdopcion("??", TipoPregunta.SINGLE);
        pregunta.agregarOpcion("as");
        pregunta.asignarOrganizacion(organizacion);
        RespuestaAdopcion respuesta=new RespuestaAdopcion(pregunta,new ArrayList<>());
        respuesta.agregarValor("a");
        publi.agregarRespuesta(respuesta);
        organizacion.addPublicacion(publi);
        publi.aprobarPublicacion();

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(raul);
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();

    }
}
