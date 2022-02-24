package domain.persistencia;

import static org.junit.Assert.*;

import domain.entities.autenticacion.Administrador;
import domain.entities.organizacion.Rescatista;
import org.junit.Test;

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
    public String direccion;

    public String nombreContacto;
    public String apellidoContacto;
    public String emailContacto;
    public String telefonoContacto;

    @Test
    public void agregarUsuarioDuenio() {
        this.nombre = "nathaas";
        this.apellido ="apellidoDeUsuario";
        this.nombreDeUsuario = "nombreDeUsuario";
        this.contrasenia = "contrasenia";
        this.documento = new Documento(12312312,"DNI");
        this.fechaNacimiento = new Date();
        this.tipo= TipoUsuario.DUENIO;
        this.direccion="xs";
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
        usuarioDuenio.setTipoDeUsuario(tipo);
        usuarioDuenio.setDireccion(direccion);

       repoUser.agregar(usuarioDuenio);

        //assertEquals(false,repoUser.estaRegistradoBoolean("usuarioo"));
        //repoUser.eliminar(usuarioDuenio);
    }
    @Test
    public void agregarUsuarioRescatista() {
        this.nombre = "natha";
        this.apellido = "apellidoDeUsuario";
        this.nombreDeUsuario = "as";
        this.contrasenia = "senia";
        this.documento = new Documento(12312312, "DNI");
        this.fechaNacimiento = new Date();
        this.direccion="lol";

        List<MedioDeNotificacion> medios = new ArrayList<>();
        MedioDeNotificacion medio = new MedioDeNotificacion();
        medio.setEstrategiaNotificacion(new Sms());
        medios.add(medio);
        this.contactos.add(new Contacto(nombreContacto, apellidoContacto, emailContacto, telefonoContacto, medios));

        Rescatista usuarioRescatista = new Rescatista(nombre, contrasenia);

        usuarioRescatista.setNombre(nombreDeUsuario);
        usuarioRescatista.setApellido(apellido);
        usuarioRescatista.setFechaNacimiento(fechaNacimiento);
        usuarioRescatista.setDocumento(documento);
        usuarioRescatista.setMediosDeContacto(contactos);
        usuarioRescatista.setDireccion(direccion);


        repoUser.agregar(usuarioRescatista);
    }
    @Test
    public void agregarUsuarioAdmin(){
        this.nombre = "admin";
        this.apellido = "apellidoDeadmin";
        this.nombreDeUsuario = "tumama";
        this.contrasenia = "seniasada";
        this.documento = new Documento(12312312, "DNI");
        this.fechaNacimiento = new Date();
        this.direccion="lol";

        List<MedioDeNotificacion> medios = new ArrayList<>();
        MedioDeNotificacion medio = new MedioDeNotificacion();
        medio.setEstrategiaNotificacion(new Sms());
        medios.add(medio);
        this.contactos.add(new Contacto(nombreContacto, apellidoContacto, emailContacto, telefonoContacto, medios));

        Administrador admin=new Administrador(nombreDeUsuario,contrasenia);
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setFechaNacimiento(fechaNacimiento);
        admin.setDocumento(documento);
        admin.setMediosDeContacto(contactos);
        admin.setDireccion(direccion);

        repoUser.modificar(admin);
    }

    @Test
    public void chequearRegistroUsuario(){
       assertEquals(false, this.repoUser.estaRegistradoBoolean("usuarionashe"));
    }
}