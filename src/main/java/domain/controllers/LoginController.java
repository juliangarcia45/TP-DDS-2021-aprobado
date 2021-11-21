package domain.controllers;

import domain.entities.autenticacion.Usuario;
import domain.entities.notificacion.Contacto;
import domain.entities.notificacion.Email;
import domain.entities.notificacion.MedioDeNotificacion;
import domain.entities.notificacion.Sms;
import domain.entities.notificacion.WhatsApp;
import domain.entities.organizacion.Documento;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.TipoUsuario;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.github.jknack.handlebars.Handlebars;

public class LoginController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);


    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }

    public ModelAndView signUp(Request request, Response response){
        Map<String,Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"register/newUser.hbs");
    }
    public Response login(Request request, Response response){
        try{
           // Repositorio repoUsuarios = FactoryRepositorio.get();
            
            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contrasenia     = request.queryParams("contrasenia");
            if(repoUser.existe(nombreDeUsuario, contrasenia)){
                Usuario usuario = repoUser.buscarUsuarioExistente(nombreDeUsuario, contrasenia);

                request.session(true);
                request.session().attribute("id", usuario.getId());
                response.status(200);
                response.redirect("/home");
             }
             else{
                 request.session(false);
                 response.redirect("/");
             }
         
        }
        catch (Exception e){
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.status(404);
            response.redirect("/");
        }
        finally {
            return response;
        }
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

    public Response registrar(Request request, Response response){
        try{
            // Repositorio repoUsuarios = FactoryRepositorio.get();
             
             String nombreDeUsuario = request.queryParams("nombreDeUsuario");
             String contrasenia     = request.queryParams("contrasenia");
     
             String nombre = request.queryParams("nombre");
             String apellido = request.queryParams("apellido");
             String fecString = request.queryParams("fechaNacimiento");
             String docString =request.queryParams("docNumb");
             String docTipoString = request.queryParams("tipoDoc");

             String nombreContacto = request.queryParams("nombreContacto");
             String apellidoContacto = request.queryParams("apellidoContacto");
             String emailContacto = request.queryParams("emailContacto");
             String telefonoContacto = request.queryParams("telefonoContacto");
             String preferencia = request.queryParams("preferenciaNotificacion");
     
           if(!repoUser.estaRegistradoBoolean(nombreDeUsuario)){
                MedioDeNotificacion medio = new MedioDeNotificacion();
                Duenio nuevoDuenio = new Duenio(nombreDeUsuario,contrasenia);
                nuevoDuenio.setApellido(apellido);
                nuevoDuenio.setNombre(nombre);
                nuevoDuenio.setFechaNacimiento(new SimpleDateFormat("yyyy/MM/dd").parse(fecString));
                nuevoDuenio.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
                
                switch(preferencia){
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
                Contacto contacto = new Contacto(nombreContacto,apellidoContacto,emailContacto,Integer.parseInt(telefonoContacto),medios);
                List<Contacto> contactos = new ArrayList<>();
                contactos.add(contacto);
                nuevoDuenio.setMediosDeContacto(contactos);
                nuevoDuenio.setTipoUsuario(TipoUsuario.DUENIO);
                repoUser.agregar(nuevoDuenio);

                response.status(200);
                response.redirect("/home");
            }
            else{
                response.status(404);
                response.redirect("/");
            }
          
            
        }
         catch (Exception e){
             //Funcionalidad disponible solo con persistencia en Base de Datos
             response.status(406);
             response.redirect("/");
         }
    finally 
    {return response;}
     
    }
}
