package domain.controllers;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeOrganizaciones;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.RepositorioDeVoluntarios;
import domain.entities.autenticacion.Usuario;
import domain.entities.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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
        return new ModelAndView(parametros,"admin/adminHome.hbs");
    }
    public Response verificarAdmin(Request request, Response response){
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario adminEnSesion= repoUser.buscar(idUsuarioEnSesion);
        if (adminEnSesion.getTipoUsuario()== TipoUsuario.ADMINISTRADOR){response.status(200);}
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
