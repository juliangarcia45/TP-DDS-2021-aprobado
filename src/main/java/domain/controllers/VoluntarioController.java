package domain.controllers;

import domain.Repositorios.*;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.entities.PreguntasAdopcion.Opcion;
import domain.entities.PreguntasAdopcion.PreguntaAdopcion;
import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.autenticacion.Administrador;
import domain.entities.autenticacion.Usuario;
import domain.entities.autenticacion.validadorDeContrasenias.ValidadorContrasenias;
import domain.entities.notificacion.*;
import domain.entities.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoluntarioController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    private DAO<Voluntario> daoVoluntarios = new DAOHibernate<>(Voluntario.class);
    private RepositorioDeVoluntarios repoVoluntarios = new RepositorioDeVoluntarios(daoVoluntarios);

    private DAO<Organizacion> daoOrg = new DAOHibernate<>(Organizacion.class);
    private RepositorioDeOrganizaciones repoOrg = new RepositorioDeOrganizaciones(daoOrg);

    private DAO<PublicacionMascotaEnAdopcion> daoPubliA = new DAOHibernate<>(PublicacionMascotaEnAdopcion.class);
    private RepositorioDePublicacionesEnAdopcion repoPubliA = new RepositorioDePublicacionesEnAdopcion(daoPubliA);

    private DAO<PublicacionMascotaPerdida> daoPubliP = new DAOHibernate<>(PublicacionMascotaPerdida.class);
    private RepositorioDePublicacionesMascPerdidas repoPubliP = new RepositorioDePublicacionesMascPerdidas(daoPubliP);

    private DAO<PreguntaAdopcion> daoPreguntas = new DAOHibernate<>(PreguntaAdopcion.class);
    private RepositorioDePreguntasAdopcion repoPreguntas = new RepositorioDePreguntasAdopcion(daoPreguntas);

    boolean faltanDatosUser;
    boolean usuarioEnUso;
    boolean contraseniaInvalida;

    public ModelAndView voluntarioHome(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idVoluntarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idVoluntarioEnSesion);
        Integer cantVoluntariosEnLaOrganizacion =repoVoluntarios.buscarPorOrganizacion(voluntarioEnSesion.getOrganizacion()).size();
        List<Publicacion> publicacionesMascEnAdopcion=voluntarioEnSesion.getOrganizacion().publicacionesDeMascotasEnAdopcionEnEspera();
        List<PublicacionMascotaEnAdopcion> publicacionesNoAprobadasAdopcion=new ArrayList<>();
        for(Publicacion publicacion:publicacionesMascEnAdopcion){
            publicacionesNoAprobadasAdopcion.add(repoPubliA.buscar(publicacion.getId()));
        }
        List<Publicacion> publicacionesMascPerdidas=voluntarioEnSesion.getOrganizacion().publicacionesDeMascotasPerdidasEnEspera();
        List<PublicacionMascotaPerdida> publicacionesNoAprobadasPerdidas=new ArrayList<>();
        for(Publicacion publicacion:publicacionesMascPerdidas){
            publicacionesNoAprobadasPerdidas.add(repoPubliP.buscar(publicacion.getId()));
        }
        Integer cantPublicacionesNoAprob= publicacionesNoAprobadasAdopcion.size()+publicacionesMascEnAdopcion.size();
        parametros.put("voluntarioNombre",voluntarioEnSesion.getUsuario());
        parametros.put("cantVoluntarios",cantVoluntariosEnLaOrganizacion);
        parametros.put("cantPublicaciones",cantPublicacionesNoAprob);
        parametros.put("voluntario",true);
        return new ModelAndView(parametros,"admin/adminHome.hbs");
    }

    public ModelAndView listadoVoluntarios(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        List<Voluntario> voluntariosEnLaOrganizacion=repoVoluntarios.buscarPorOrganizacion(voluntarioEnSesion.getOrganizacion());
        parametros.put("voluntarioNombre",voluntarioEnSesion.getUsuario());
        parametros.put("voluntarios",voluntariosEnLaOrganizacion);
        parametros.put("voluntario",true);
        return new ModelAndView(parametros,"admin/listadoVoluntarios.hbs");
    }

    public ModelAndView agregarVoluntario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("registrar",true);
        parametros.put("voluntario",true);
        if(faltanDatosUser){
            parametros.put("faltanDatos",true);
        }
        if(usuarioEnUso){
            parametros.put("usuarioEnUso",true);
            usuarioEnUso=false;
        }
        if(contraseniaInvalida){
            parametros.put("contraseniaInvalida",true);
            contraseniaInvalida=false;
        }
        return new ModelAndView(parametros,"admin/listadoUsuariosAgregar.hbs");
    }

    public Response registrarVoluntario(Request request, Response response) throws ParseException {
        String nombreDeUsuario = request.queryParams("usuario");
        String contrasenia = request.queryParams("contrasenia");
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String fecString = request.queryParams("fechaDeNacimiento");
        String docString = request.queryParams("numDoc");
        String docTipoString = request.queryParams("tipoDoc");
        String direccion = request.queryParams("direccion");
        String nombreContacto = request.queryParams("nombreContacto");
        String apellidoContacto = request.queryParams("apellidoContacto");
        String emailContacto = request.queryParams("emailContacto");
        String telefonoContacto = request.queryParams("telefonoContacto");
        String sms = request.queryParams("prefSMS");
        String wpp = request.queryParams("prefWPP");
        String mail = request.queryParams("prefMAIL");

        //valido los datos
        if(nombreDeUsuario.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(contrasenia.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(nombre.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(apellido.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(fecString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(docString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(docTipoString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(direccion.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(nombreContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(apellidoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(emailContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(telefonoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(sms.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(wpp.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(mail.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }
        if(!ValidadorContrasenias.validar(contrasenia)){
            contraseniaInvalida=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
            return response;
        }

        //registro al usuario si no esta registrado
        if (!repoUser.estaRegistradoBoolean(nombreDeUsuario)){
            MedioDeNotificacion mensTexto = new MedioDeNotificacion();
            MedioDeNotificacion whatsapp= new MedioDeNotificacion();
            MedioDeNotificacion email= new MedioDeNotificacion();
            List<MedioDeNotificacion> medios = new ArrayList<>();
            if (sms.equals("SMS")){
                mensTexto.setEstrategiaNotificacion(new Sms());
                medios.add(mensTexto);
            }
            if (wpp.equals("WPP")){
                whatsapp.setEstrategiaNotificacion(new WhatsApp());
                medios.add(whatsapp);
            }
            if (mail.equals("MAIL")){
                email.setEstrategiaNotificacion(new Email());
                medios.add(email);
            }
            Contacto contacto=new Contacto(nombreContacto,apellidoContacto,emailContacto,telefonoContacto,medios);
            List<Contacto> contactos=new ArrayList<>();
            contactos.add(contacto);
            Voluntario voluntario=new Voluntario(nombreDeUsuario,contrasenia);
            voluntario.setNombre(nombre);
            voluntario.setApellido(apellido);
            voluntario.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
            voluntario.setDireccion(direccion);
            voluntario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
            voluntario.setMediosDeContacto(contactos);
            int idUsuarioEnSesion= request.session().attribute("id");
            Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
            voluntario.setOrganizacion(voluntarioEnSesion.getOrganizacion());
            repoVoluntarios.agregar(voluntario);
            response.status(200);
            response.redirect("/listaVoluntarios");
        }else{
            usuarioEnUso=true;
            response.status(400);
            response.redirect("/agregarVoluntario");
        }
        return response;
    }

    public ModelAndView editarVoluntario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Voluntario usuario=repoVoluntarios.buscar(id);
        List<Contacto> contactos=usuario.getMediosDeContacto();
        parametros.put("usuario",usuario);
        parametros.put("contactos",contactos);
        parametros.put("editar",true);
        parametros.put("voluntario",true);
        if(faltanDatosUser){
            parametros.put("faltanDatos",true);
        }
        if(usuarioEnUso){
            parametros.put("usuarioEnUso",true);
            usuarioEnUso=false;
        }
        if(contraseniaInvalida){
            parametros.put("contraseniaInvalida",true);
            contraseniaInvalida=false;
        }
        return new ModelAndView(parametros,"admin/listadoUsuariosAgregar.hbs");
    }

    public Response actualizarVoluntario(Request request, Response response) throws ParseException {
        String id=request.queryParams("id");
        String nombreDeUsuario = request.queryParams("usuario");
        String contrasenia = request.queryParams("contrasenia");
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String fecString = request.queryParams("fechaDeNacimiento");
        String docString = request.queryParams("numDoc");
        String docTipoString = request.queryParams("tipoDoc");
        String direccion = request.queryParams("direccion");
        String nombreContacto = request.queryParams("nombreContacto");
        String apellidoContacto = request.queryParams("apellidoContacto");
        String emailContacto = request.queryParams("emailContacto");
        String telefonoContacto = request.queryParams("telefonoContacto");
        String sms = request.queryParams("prefSMS");
        String wpp = request.queryParams("prefWPP");
        String mail = request.queryParams("prefMAIL");

        //valido los datos
        if(nombreDeUsuario.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(contrasenia.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(nombre.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(apellido.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(fecString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(docString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(docTipoString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(direccion.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(nombreContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(apellidoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(emailContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(telefonoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(sms.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(wpp.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(mail.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }
        if(!ValidadorContrasenias.validar(contrasenia)){
            contraseniaInvalida=true;
            response.status(400);
            response.redirect("/editarVoluntario/"+id);
            return response;
        }

        MedioDeNotificacion mensTexto = new MedioDeNotificacion();
        MedioDeNotificacion whatsapp= new MedioDeNotificacion();
        MedioDeNotificacion email= new MedioDeNotificacion();
        List<MedioDeNotificacion> medios = new ArrayList<>();
        if (sms.equals("SMS")){
            mensTexto.setEstrategiaNotificacion(new Sms());
            medios.add(mensTexto);
        }else{
            for(MedioDeNotificacion medio:medios){
                if(medio.getEstrategiaNotificacion().getClass()==Sms.class){
                    medios.remove(medio);
                }
            }
        }
        if (wpp.equals("WPP")){
            whatsapp.setEstrategiaNotificacion(new WhatsApp());
            medios.add(whatsapp);
        }else{
            for(MedioDeNotificacion medio:medios){
                if(medio.getEstrategiaNotificacion().getClass()==WhatsApp.class){
                    medios.remove(medio);
                }
            }
        }
        if (mail.equals("MAIL")){
            email.setEstrategiaNotificacion(new Email());
            medios.add(email);
        }else{
            for(MedioDeNotificacion medio:medios){
                if(medio.getEstrategiaNotificacion().getClass()==Email.class){
                    medios.remove(medio);
                }
            }
        }

        Contacto contacto=new Contacto(nombreContacto,apellidoContacto,emailContacto,telefonoContacto,medios);
        List<Contacto> contactos=new ArrayList<>();
        contactos.add(contacto);
        Voluntario voluntario=new Voluntario(nombreDeUsuario,contrasenia);
        voluntario.setNombre(nombre);
        voluntario.setApellido(apellido);
        voluntario.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
        voluntario.setDireccion(direccion);
        voluntario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
        voluntario.setMediosDeContacto(contactos);
        repoVoluntarios.modificar(voluntario);
        response.status(200);
        response.redirect("/listaVoluntarios");
        return response;
    }

    public ModelAndView verVoluntario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Voluntario usuario=repoVoluntarios.buscar(id);
        List<Contacto> contactos=usuario.getMediosDeContacto();
        parametros.put("usuario",usuario);
        parametros.put("contactos",contactos);
        parametros.put("ver",true);
        parametros.put("voluntario",true);
        return new ModelAndView(parametros,"admin/listadoUsuariosAgregar.hbs");
    }

    public ModelAndView borrarVoluntario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Voluntario usuario= repoVoluntarios.buscar(Integer.parseInt(request.params("id")));
        repoVoluntarios.eliminar(usuario);
        return new ModelAndView(parametros,"admin/listadoUsuarios.hbs");
    }

    //Lista publicaciones no aprobadas
    public ModelAndView listadoPublicacionesAdopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        List<Publicacion> publicaciones=voluntarioEnSesion.getOrganizacion().publicacionesDeMascotasEnAdopcionEnEspera();
        List<PublicacionMascotaEnAdopcion> publicacionesNoAprobadas=new ArrayList<>();
        for(Publicacion publicacion:publicaciones){
            publicacionesNoAprobadas.add(repoPubliA.buscar(publicacion.getId()));
        }
        parametros.put("voluntarioNombre",voluntarioEnSesion.getUsuario());
        parametros.put("publicaciones",publicacionesNoAprobadas);
        return new ModelAndView(parametros,"voluntario/listadoPublicacionesAdopcion.hbs");
    }

    public ModelAndView verPublicacionAdopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        PublicacionMascotaEnAdopcion publicacion=repoPubliA.buscar(id);
        List<RespuestaAdopcion> respuestas=publicacion.getRespuestas();
        parametros.put("publicacion",publicacion);
        parametros.put("pregunta",respuestas);
        parametros.put("adopcion",true);
        return new ModelAndView(parametros,"voluntario/verPublicacion.hbs");
    }

    public Response aprobarPublicacionAdopcion(Request request, Response response){
        String id=request.queryParams("id");
        PublicacionMascotaEnAdopcion publicacion=repoPubliA.buscar(Integer.parseInt(id));
        publicacion.aprobarPublicacion();
        response.status(200);
        response.redirect("/listaPublicacionesAdopcion");
        return response;
    }

    public ModelAndView borrarPublicacionAdopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        voluntarioEnSesion.getOrganizacion().getListaPublicaciones().remove(repoPubliA.buscar(id));
        repoOrg.modificar(voluntarioEnSesion.getOrganizacion());
        return new ModelAndView(parametros,"voluntario/listadoPublicacionesAdopcion.hbs");
    }

    public ModelAndView listadoPublicacionesPerdidas(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        List<Publicacion> publicaciones=voluntarioEnSesion.getOrganizacion().publicacionesDeMascotasPerdidasEnEspera();
        List<PublicacionMascotaPerdida> publicacionesNoAprobadas=new ArrayList<>();
        for(Publicacion publicacion:publicaciones){
            publicacionesNoAprobadas.add(repoPubliP.buscar(publicacion.getId()));
        }
        parametros.put("voluntarioNombre",voluntarioEnSesion.getUsuario());
        parametros.put("publicaciones",publicacionesNoAprobadas);
        return new ModelAndView(parametros,"voluntario/listadoPublicacionesMascPerdidas.hbs");
    }

    public ModelAndView verPublicacionPerdida(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        PublicacionMascotaPerdida publicacion=repoPubliP.buscar(id);
        parametros.put("publicacion",publicacion);
        parametros.put("perdida",true);
        return new ModelAndView(parametros,"voluntario/verPublicacion.hbs");
    }

    public Response aprobarPublicacionPerdida(Request request, Response response){
        String id=request.queryParams("id");
        PublicacionMascotaPerdida publicacion=repoPubliP.buscar(Integer.parseInt(id));
        publicacion.aprobarPublicacion();
        response.status(200);
        response.redirect("/listaPublicacionesPerdidas");
        return response;
    }

    public ModelAndView borrarPublicacionPerdida(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        voluntarioEnSesion.getOrganizacion().getListaPublicaciones().remove(repoPubliP.buscar(id));
        repoOrg.modificar(voluntarioEnSesion.getOrganizacion());
        return new ModelAndView(parametros,"voluntario/listadoPublicacionesMascPerdidas.hbs");
    }

    public ModelAndView preguntasDeLaOrganizacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        List<PreguntaAdopcion> preguntas=voluntarioEnSesion.getOrganizacion().getPreguntas();
        parametros.put("voluntarioNombre",voluntarioEnSesion.getUsuario());
        parametros.put("preguntas",preguntas);
        return new ModelAndView(parametros,"voluntario/listadoPreguntas.hbs");
    }

    public ModelAndView agregarPreguntaALaOrganizacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        List<PreguntaAdopcion> preguntasOrganizacion=voluntarioEnSesion.getOrganizacion().getPreguntas();
        List<PreguntaAdopcion> preguntasGenerales=repoPreguntas.buscarTodos();
        for(PreguntaAdopcion pregunta:preguntasOrganizacion){
            preguntasGenerales.remove(pregunta);
        }
        parametros.put("preguntas",preguntasGenerales);
        return new ModelAndView(parametros,"voluntario/agregarPregunta.hbs");
    }

    public Response agregarPregunta(Request request, Response response){
        String idPregunta=request.queryParams("pregunta");
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        voluntarioEnSesion.getOrganizacion().getPreguntas().add(repoPreguntas.buscar(Integer.parseInt(idPregunta)));
        repoOrg.modificar(voluntarioEnSesion.getOrganizacion());
        response.status(200);
        response.redirect("voluntario/listadoPreguntas.hbs");
        return response;
    }

    public ModelAndView verPregunta(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        PreguntaAdopcion pregunta=repoPreguntas.buscar(id);
        List<Opcion> respuestas=pregunta.getOpciones();
        parametros.put("pregunta",pregunta);
        parametros.put("respuesta",respuestas);
        return new ModelAndView(parametros,"voluntario/verPregunta.hbs");
    }

    public ModelAndView borrarPregunta(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        int idUsuarioEnSesion= request.session().attribute("id");
        Voluntario voluntarioEnSesion= repoVoluntarios.buscar(idUsuarioEnSesion);
        voluntarioEnSesion.getOrganizacion().getPreguntas().remove(repoPreguntas.buscar(id));
        repoOrg.modificar(voluntarioEnSesion.getOrganizacion());
        return new ModelAndView(parametros,"voluntario/listadoPreguntas.hbs");
    }

}
