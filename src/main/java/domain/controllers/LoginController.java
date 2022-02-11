package domain.controllers;

import domain.entities.autenticacion.Usuario;
import domain.entities.autenticacion.validadorDeContrasenias.ValidadorContrasenias;
import domain.entities.notificacion.Contacto;
import domain.entities.notificacion.Email;
import domain.entities.notificacion.MedioDeNotificacion;
import domain.entities.notificacion.Sms;
import domain.entities.notificacion.WhatsApp;
import domain.entities.organizacion.Documento;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.Rescatista;
import domain.entities.organizacion.TipoUsuario;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoginController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    //Errores de registro
    boolean[] faltanDatos=new boolean[14];
    boolean contraseniaIncorrecta;
    boolean loginIncorrecto;

    public void inicializarFaltanDatos(){
        faltanDatos[0]=false;
        faltanDatos[1]=false;
        faltanDatos[2]=false;
        faltanDatos[3]=false;
        faltanDatos[4]=false;
        faltanDatos[5]=false;
        faltanDatos[6]=false;
        faltanDatos[7]=false;
        faltanDatos[8]=false;
        faltanDatos[9]=false;
        faltanDatos[10]=false;
        faltanDatos[11]=false;
        faltanDatos[12]=false;
        faltanDatos[13]=false;
    }

    public boolean faltanDatosXd(){
        boolean faltan=false;
        for(boolean dato: faltanDatos){
            if(dato==true){
                faltan=true;
            }
        }
        return faltan;
    }

    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Map<String,Object> errorParameters=new HashMap<>();
        if(loginIncorrecto){
            errorParameters.put("loginIncorrecto", true);
            return new ModelAndView(errorParameters, "login.hbs");
        }
        return new ModelAndView(parametros,"login.hbs");
    }

    public ModelAndView signUp(Request request, Response response){
        Map<String,Object> parametros = new HashMap<>();
        Map<String,Object> errorParameters=new HashMap<>();
        if(faltanDatosXd()){
            errorParameters.put("faltanDatos", true);
            return new ModelAndView(errorParameters, "register/newUser.hbs");
        }
        else if(contraseniaIncorrecta){
            errorParameters.put("contraseniaIncorrecta",true);
            return new ModelAndView(errorParameters, "register/newUser.hbs");
        }
        else{
            return new ModelAndView(parametros,"register/newUser.hbs");
        }
    }
    public Response login(Request request, Response response) throws NoResultException {
        // Repositorio repoUsuarios = FactoryRepositorio.get();

        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia = request.queryParams("contrasenia");
        if (repoUser.estaRegistradoBooleanContrasenia(nombreDeUsuario, contrasenia)) {
            Usuario usuario = repoUser.buscarUsuarioExistente(nombreDeUsuario, contrasenia);
            loginIncorrecto=false;

            request.session(true);
            request.session().attribute("id", usuario.getId());
            response.status(200);
            if (usuario.getTipoUsuario() == TipoUsuario.DUENIO) {
                response.redirect("/adopcion");
            } else if (usuario.getTipoUsuario() == TipoUsuario.RESCATISTA) {
                response.redirect("/home");
            } else if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                response.redirect("/home");
            } else {
                response.redirect("/home");
            }

        } else {
            loginIncorrecto = true;
            request.session(false);
            response.status(404);
            response.redirect("/");
        }
        return response;

    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

    public boolean validarDatosRegistro(String usuario, String contrasenia, String nombre, String apellido, String fecNac, String tipoDoc, String numDoc, String direccion, String nombreContacto, String apellidoContacto, String email, String telefono, String prefNotif, String tipoUsuario ) {
        inicializarFaltanDatos();
        contraseniaIncorrecta=false;
        int validacion=0;

        if (usuario.isEmpty()) {
            faltanDatos[0] = true;
        }

        if (contrasenia.isEmpty()) {
            faltanDatos[1] = true;
        }

        if (nombre.isEmpty()) {
            faltanDatos[2] = true;
        }

        if (apellido.isEmpty()) {
            faltanDatos[3] = true;
        }

        if (fecNac.isEmpty()) {
            faltanDatos[4] = true;
        }

        if (tipoDoc.isEmpty()) {
            faltanDatos[5] = true;
        }

        if (numDoc.isEmpty()) {
            faltanDatos[6] = true;
        }

        if (direccion.isEmpty()) {
            faltanDatos[7] = true;
        }

        if (nombreContacto.isEmpty()) {
            faltanDatos[8] = true;
        }

        if (apellidoContacto.isEmpty()) {
            faltanDatos[9] = true;
        }

        if (email.isEmpty()) {
            faltanDatos[10] = true;
        }

        if (telefono.isEmpty()) {
            faltanDatos[11] = true;
        }

        if (prefNotif.isEmpty()) {
            faltanDatos[12] = true;
        }

        if (tipoUsuario.isEmpty()) {
            faltanDatos[13] = true;
        }

        if(!ValidadorContrasenias.validar(contrasenia)){
        contraseniaIncorrecta=true;
        }

        for(boolean dato: faltanDatos){
            if (dato==true){
                validacion++;
            }
        }
        if(validacion==0 && !contraseniaIncorrecta){
            return true;
        }else{
            return false;
        }
    }


    public Response registrar(Request request, Response response) throws ParseException {
        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia = request.queryParams("contrasenia");
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String fecString = request.queryParams("fechaNacimiento");
        String docString = request.queryParams("docNumb");
        String docTipoString = request.queryParams("tipoDoc");
        String direccion = request.queryParams("direccion");
        String nombreContacto = request.queryParams("nombreContacto");
        String apellidoContacto = request.queryParams("apellidoContacto");
        String emailContacto = request.queryParams("emailContacto");
        String telefonoContacto = request.queryParams("telefonoContacto");
        String preferencia = request.queryParams("preferenciaNotificacion");
        String tipoUsuario = request.queryParams("tipoUsuario");


        if (validarDatosRegistro(nombreDeUsuario,contrasenia,nombre,apellido,fecString,docTipoString,docString,direccion,nombreContacto,apellidoContacto,emailContacto,telefonoContacto,preferencia,tipoUsuario) && !repoUser.estaRegistradoBoolean(nombreDeUsuario)) {
            if (tipoUsuario == "DUENIO") {
                MedioDeNotificacion medio = new MedioDeNotificacion();
                Duenio nuevoDuenio = new Duenio(nombreDeUsuario, contrasenia);
                nuevoDuenio.setApellido(apellido);
                nuevoDuenio.setNombre(nombre);
                nuevoDuenio.setFechaNacimiento(new SimpleDateFormat("yyyy/MM/dd").parse(fecString));
                nuevoDuenio.setDocumento(new Documento(Float.parseFloat(docString), docTipoString));
                nuevoDuenio.setDireccion(direccion);

                switch (preferencia) {
                    case "SMS":
                        medio.setEstrategiaNotificacion(new Sms());
                        break;
                    case "WPP":
                        medio.setEstrategiaNotificacion(new WhatsApp());
                        break;
                    case "MAIL":
                        medio.setEstrategiaNotificacion(new Email());
                        break;
                    default:
                        break;
                }
                List<MedioDeNotificacion> medios = new ArrayList<>();
                medios.add(medio);
                Contacto contacto = new Contacto(nombreContacto, apellidoContacto, emailContacto, Integer.parseInt(telefonoContacto), medios);
                List<Contacto> contactos = new ArrayList<>();
                contactos.add(contacto);
                nuevoDuenio.setMediosDeContacto(contactos);
                repoUser.agregar(nuevoDuenio);

                response.status(200);
                response.redirect("/home");
            } else {
                MedioDeNotificacion medio = new MedioDeNotificacion();
                Rescatista nuevoRescatista = new Rescatista(nombreDeUsuario, contrasenia);
                nuevoRescatista.setApellido(apellido);
                nuevoRescatista.setNombre(nombre);
                nuevoRescatista.setFechaNacimiento(new SimpleDateFormat("yyyy/MM/dd").parse(fecString));
                nuevoRescatista.setDocumento(new Documento(Float.parseFloat(docString), docTipoString));
                nuevoRescatista.setDireccion(direccion);

                switch (preferencia) {
                    case "SMS":
                        medio.setEstrategiaNotificacion(new Sms());
                        break;
                    case "WPP":
                        medio.setEstrategiaNotificacion(new WhatsApp());
                        break;
                    case "MAIL":
                        medio.setEstrategiaNotificacion(new Email());
                        break;
                    default:
                        break;
                }
                List<MedioDeNotificacion> medios = new ArrayList<>();
                medios.add(medio);
                Contacto contacto = new Contacto(nombreContacto, apellidoContacto, emailContacto, Integer.parseInt(telefonoContacto), medios);
                List<Contacto> contactos = new ArrayList<>();
                contactos.add(contacto);
                nuevoRescatista.setMediosDeContacto(contactos);
                repoUser.agregar(nuevoRescatista);

                response.status(200);
                response.redirect("/home");
            }
        } else {
            response.status(404);
            response.redirect("/signUp");
        }

        return response;
    }
}
