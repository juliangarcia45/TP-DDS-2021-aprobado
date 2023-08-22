package domain.controllers;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeOrganizaciones;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.RepositorioDeVoluntarios;
import domain.entities.autenticacion.Administrador;
import domain.entities.autenticacion.Usuario;
import domain.entities.autenticacion.validadorDeContrasenias.ValidadorContrasenias;
import domain.entities.notificacion.*;
import domain.entities.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    private DAO<Voluntario> daoVoluntarios = new DAOHibernate<>(Voluntario.class);
    private RepositorioDeVoluntarios repoVoluntarios = new RepositorioDeVoluntarios(daoVoluntarios);

    private DAO<Organizacion> daoOrg = new DAOHibernate<>(Organizacion.class);
    private RepositorioDeOrganizaciones repoOrg = new RepositorioDeOrganizaciones(daoOrg);

    private DAO<Publicacion> daoPubli = new DAOHibernate<>(Publicacion.class);
    private RepositorioDePublicaciones repoPubli = new RepositorioDePublicaciones(daoPubli);

    boolean faltanDatosOrg;
    boolean faltanDatosUser;
    boolean usuarioEnUso;
    boolean contraseniaInvalida;

    public ModelAndView adminHome(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer cantVoluntarios =repoUser.buscarPorTipo(TipoUsuario.VOLUNTARIO).size();
        Integer cantUsuarios= repoUser.buscarTodosLosUsuarios().size();
        Integer cantOrganizaciones=repoOrg.buscarTodasLasOrganizaciones().size();
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        parametros.put("adminNombre",adminEnSesion.getUsuario());
        parametros.put("cantVoluntarios",cantVoluntarios);
        parametros.put("cantUsuarios",cantUsuarios);
        parametros.put("cantOrganizaciones",cantOrganizaciones);
        parametros.put("admin",true);
        return new ModelAndView(parametros,"admin/adminHome.hbs");
    }
    public Response verificarAdmin(Request request, Response response){
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        if (adminEnSesion.getTipoDeUsuario()== TipoUsuario.ADMINISTRADOR){response.status(200);}
        else {response.status(406);
            response.redirect("/home");}
        return response;
    }

    public ModelAndView listadoOrganizaciones(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<Organizacion> organizaciones=repoOrg.buscarTodasLasOrganizaciones();
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        parametros.put("adminNombre",adminEnSesion.getUsuario());
        parametros.put("organizaciones",organizaciones);
        return new ModelAndView(parametros,"admin/listadoOrganizaciones.hbs");
    }

    public ModelAndView addOrganizacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if(faltanDatosOrg){
            parametros.put("faltanDatos",true);
        }
        parametros.put("registrar",true);
        return new ModelAndView(parametros,"admin/listadoOrganizacionesAgregar.hbs");
    }

    public Response registrarOrganizacion(Request request, Response response){
        faltanDatosOrg=false;
        String nombreOrganizacion=request.queryParams("nombre");
        String latitud=request.queryParams("latitud");
        String longitud=request.queryParams("longitud");
        String direccion=request.queryParams("direccion");

        if(nombreOrganizacion.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/agregarOrganizacion");
            return response;
        }
        if(latitud.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/agregarOrganizacion");
            return response;
        }
        if(longitud.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/agregarOrganizacion");
            return response;
        }
        if(direccion.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/agregarOrganizacion");
            return response;
        }

        Ubicacion ubicacionOrganizacion=new Ubicacion();
        ubicacionOrganizacion.setLatitud(Double.parseDouble(latitud));
        ubicacionOrganizacion.setLongitud(Double.parseDouble(longitud));
        Organizacion organizacion=new Organizacion();
        organizacion.setNombre(nombreOrganizacion);
        organizacion.setDireccion(direccion);
        organizacion.setUbicacion(ubicacionOrganizacion);
        repoOrg.agregar(organizacion);
        response.status(200);
        response.redirect("/listadoOrganizaciones");
        return response;
    }

    public ModelAndView editarOrganizacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Organizacion organizacion=repoOrg.buscar(id);
        parametros.put("organizacion",organizacion);
        if(faltanDatosOrg){
            parametros.put("faltanDatos",true);
        }
        parametros.put("editar",true);
        return new ModelAndView(parametros,"admin/listadoOrganizacionesAgregar.hbs");
    }

    public Response actualizarOrganizacion(Request request, Response response){
        faltanDatosOrg=false;
        String nombreOrganizacion=request.queryParams("nombre");
        String latitud=request.queryParams("latitud");
        String longitud=request.queryParams("longitud");
        String direccion=request.queryParams("direccion");
        if(nombreOrganizacion.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/editarOrganizacion/" + request.queryParams("id"));
            return response;
        }
        if(latitud.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/editarOrganizacion/" + request.queryParams("id"));
            return response;
        }
        if(longitud.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/editarOrganizacion/" + request.queryParams("id"));
            return response;
        }
        if(direccion.isEmpty()){
            faltanDatosOrg=true;
            response.status(400);
            response.redirect("/editarOrganizacion/" + request.queryParams("id"));
            return response;
        }
        Ubicacion ubicacionOrganizacion=new Ubicacion();
        ubicacionOrganizacion.setLatitud(Double.parseDouble(latitud));
        ubicacionOrganizacion.setLongitud(Double.parseDouble(longitud));
        Organizacion organizacion=repoOrg.buscar(Integer.parseInt(request.queryParams("id")));
        organizacion.setNombre(nombreOrganizacion);
        organizacion.setUbicacion(ubicacionOrganizacion);
        organizacion.setDireccion(direccion);
        repoOrg.modificar(organizacion);
        response.status(200);
        response.redirect("/listadoOrganizaciones");
        return response;
    }

    public ModelAndView verOrganizacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Organizacion organizacion=repoOrg.buscar(id);
        parametros.put("organizacion",organizacion);
        parametros.put("ver",true);
        return new ModelAndView(parametros,"admin/listadoOrganizacionesAgregar.hbs");
    }

    public ModelAndView borrarOrganizacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Organizacion organizacion= repoOrg.buscar(Integer.parseInt(request.params("id")));
        repoOrg.eliminar(organizacion);
        return new ModelAndView(parametros,"admin/listadoOrganizaciones.hbs");
    }


    public ModelAndView listadoUsuarios(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<Usuario> usuarios=repoUser.buscarTodosLosUsuarios();
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        parametros.put("adminNombre",adminEnSesion.getUsuario());
        parametros.put("usuarios",usuarios);
        return new ModelAndView(parametros,"admin/listadoUsuarios.hbs");
    }

    public ModelAndView agregarUsuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("registrar",true);
        parametros.put("admin",true);
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

    public Response registrarUsuario(Request request, Response response) throws ParseException {
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
        String tipoUsuario = request.queryParams("tipoUsuario");

        //valido los datos
        if(nombreDeUsuario.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(contrasenia.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(nombre.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(apellido.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(fecString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(docString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(docTipoString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(direccion.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(nombreContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(apellidoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(emailContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(telefonoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(sms.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(wpp.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(mail.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(tipoUsuario.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/agregarUsuario");
            return response;
        }
        if(!ValidadorContrasenias.validar(contrasenia)){
            contraseniaInvalida=true;
            response.status(400);
            response.redirect("/agregarUsuario");
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
            if(tipoUsuario.equals("DUENIO")){
                Duenio duenio=new Duenio(nombreDeUsuario,contrasenia);
                duenio.setNombre(nombre);
                duenio.setApellido(apellido);
                duenio.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
                duenio.setDireccion(direccion);
                duenio.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
                duenio.setMediosDeContacto(contactos);
                repoUser.agregar(duenio);
            }else if(tipoUsuario.equals("RESCATISTA")){
                Rescatista rescatista=new Rescatista(nombreDeUsuario,contrasenia);
                rescatista.setNombre(nombre);
                rescatista.setApellido(apellido);
                rescatista.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
                rescatista.setDireccion(direccion);
                rescatista.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
                rescatista.setMediosDeContacto(contactos);
                repoUser.agregar(rescatista);
            }else if(tipoUsuario.equals("VOLUNTARIO")){
                Voluntario voluntario=new Voluntario(nombreDeUsuario,contrasenia);
                voluntario.setNombre(nombre);
                voluntario.setApellido(apellido);
                voluntario.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
                voluntario.setDireccion(direccion);
                voluntario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
                voluntario.setMediosDeContacto(contactos);
                repoUser.agregar(voluntario);
            } else{
                Administrador administrador=new Administrador(nombreDeUsuario,contrasenia);
                administrador.setNombre(nombre);
                administrador.setApellido(apellido);
                administrador.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
                administrador.setDireccion(direccion);
                administrador.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
                administrador.setMediosDeContacto(contactos);
                repoUser.agregar(administrador);
            }
            response.status(200);
            response.redirect("/listaUsuarios");
        }else{
            usuarioEnUso=true;
            response.status(400);
            response.redirect("/agregarUsuario");
        }
        return response;
    }



    public ModelAndView editarUsuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Usuario usuario=repoUser.buscar(id);
        List<Contacto> contactos=usuario.getMediosDeContacto();
        parametros.put("usuario",usuario);
        parametros.put("contactos",contactos);
        parametros.put("editar",true);
        parametros.put("admin",true);
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

    public Response actualizarUsuario(Request request, Response response) throws ParseException {
        String id= request.queryParams("id");
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
        String tipoUsuario = request.queryParams("tipoUsuario");

        //valido los datos
        if(nombreDeUsuario.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(contrasenia.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(nombre.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(apellido.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(fecString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(docString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(docTipoString.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(direccion.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(nombreContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(apellidoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(emailContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(telefonoContacto.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(sms.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(wpp.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(mail.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }
        if(tipoUsuario.isEmpty()){
            faltanDatosUser=true;
            response.status(400);
            response.redirect("/editarUsuario/"+id);
            return response;
        }

        //registro al usuario si no esta registrado
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
        if(tipoUsuario.equals("DUENIO")){
            Duenio duenio=new Duenio(nombreDeUsuario,contrasenia);
            duenio.setNombre(nombre);
            duenio.setApellido(apellido);
            duenio.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
            duenio.setDireccion(direccion);
            duenio.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
            duenio.setMediosDeContacto(contactos);
            repoUser.modificar(duenio);
        }else if(tipoUsuario.equals("RESCATISTA")){
            Rescatista rescatista=new Rescatista(nombreDeUsuario,contrasenia);
            rescatista.setNombre(nombre);
            rescatista.setApellido(apellido);
            rescatista.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
            rescatista.setDireccion(direccion);
            rescatista.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
            rescatista.setMediosDeContacto(contactos);
            repoUser.modificar(rescatista);
        }else if(tipoUsuario.equals("VOLUNTARIO")){
            Voluntario voluntario=new Voluntario(nombreDeUsuario,contrasenia);
            voluntario.setNombre(nombre);
            voluntario.setApellido(apellido);
            voluntario.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
            voluntario.setDireccion(direccion);
            voluntario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
            voluntario.setMediosDeContacto(contactos);
            repoUser.modificar(voluntario);
        } else{
            Administrador administrador=new Administrador(nombreDeUsuario,contrasenia);
            administrador.setNombre(nombre);
            administrador.setApellido(apellido);
            administrador.setDocumento(new Documento(Float.parseFloat(docString),docTipoString));
            administrador.setDireccion(direccion);
            administrador.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(fecString));
            administrador.setMediosDeContacto(contactos);
            repoUser.modificar(administrador);
        }
        response.status(200);
        response.redirect("/listaUsuarios");
        return response;
    }

    public ModelAndView verUsuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Usuario usuario=repoUser.buscar(id);
        List<Contacto> contactos=usuario.getMediosDeContacto();
        parametros.put("usuario",usuario);
        parametros.put("contactos",contactos);
        parametros.put("ver",true);
        parametros.put("admin",true);
        return new ModelAndView(parametros,"admin/listadoUsuariosAgregar.hbs");
    }

    public ModelAndView borrarUsuario(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Usuario usuario= repoUser.buscar(Integer.parseInt(request.params("id")));
        repoUser.eliminar(usuario);
        return new ModelAndView(parametros,"admin/listadoUsuarios.hbs");
    }


    public ModelAndView listadoVoluntarios(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<Voluntario> voluntarios=repoVoluntarios.buscarTodos();
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        parametros.put("adminNombre",adminEnSesion.getUsuario());
        parametros.put("voluntarios",voluntarios);
        return new ModelAndView(parametros,"admin/listadoVoluntarios.hbs");
    }

    public ModelAndView listadoPublicaciones(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<Publicacion> publicaciones=repoPubli.buscarTodasLasPublicaciones();
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        parametros.put("adminNombre",adminEnSesion.getUsuario());
        parametros.put("publicaciones",publicaciones);
        return new ModelAndView(parametros,"admin/listadoPublicaciones.hbs");
    }


}
