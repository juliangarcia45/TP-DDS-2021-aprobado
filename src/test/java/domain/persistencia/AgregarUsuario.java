package domain.persistencia;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import domain.entities.organizacion.TipoUsuario;
import domain.entities.organizacion.Documento;
import domain.entities.organizacion.Duenio;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.entities.autenticacion.Usuario;
import domain.entities.autenticacion.validadorDeContrasenias.ValidadorContrasenias;
import domain.entities.notificacion.Contacto;
import domain.entities.notificacion.MedioDeNotificacion;
import domain.entities.notificacion.Sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  AgregarUsuario {
    
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);
    ValidadorContrasenias validadorc = ValidadorContrasenias.getInstance();
    public String nombre;
    public String apellido;
    public String nombreDeUsuario;
    public String contrasenia;
    public List<Contacto> contactos = new ArrayList<>();
    public Documento documento;
    public Date fechaNacimiento;
    public TipoUsuario tipo;

    public String nombreContacto;
    public String apellidoContacto;
    public String emailContacto;
    public int telefonoContacto;

    @Test
    public void agregarUsuarioDuenio() {
        this.nombre = "natha";
        this.apellido ="apellidoDeUsuario";
        this.nombreDeUsuario = "nombreDeUsuario";
        this.contrasenia = "contrasenia";
        this.documento = new Documento(12312312,"DNI");
        this.fechaNacimiento = new Date();
        this.tipo= TipoUsuario.DUENIO;
        List<MedioDeNotificacion> medios = new ArrayList<>(); 
        MedioDeNotificacion medio= new MedioDeNotificacion();
        medio.setEstrategiaNotificacion(new Sms());
        medios.add(medio);
        this.contactos.add(new Contacto(nombreContacto,apellidoContacto,emailContacto,telefonoContacto,medios));

        Duenio usuarioDuenio=new Duenio(nombre, contrasenia);

        usuarioDuenio.setNombre(nombreDeUsuario);
        usuarioDuenio.setApellido(apellido);
        usuarioDuenio.setFechaNacimiento(fechaNacimiento);
        usuarioDuenio.setDocumento(documento);
        usuarioDuenio.setMediosDeContacto(contactos);
        usuarioDuenio.setTipoUsuario(tipo);

       // repoUser.agregar(usuarioDuenio);

        assertEquals(false,repoUser.estaRegistradoBoolean("usuarioo"));
        //repoUser.eliminar(usuarioDuenio);
    }

    @Test
    public void chequearRegistroUsuario(){
       assertEquals(false, this.repoUser.estaRegistradoBoolean("usuarionashe"));
    }
}