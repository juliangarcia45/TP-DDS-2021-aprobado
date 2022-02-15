package domain.controllers;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeOrganizaciones;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.entities.autenticacion.Usuario;
import domain.entities.organizacion.Organizacion;
import domain.entities.organizacion.TipoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);
    private DAO<Organizacion> daoOrg = new DAOHibernate<>(Organizacion.class);
    private RepositorioDeOrganizaciones repoOrg = new RepositorioDeOrganizaciones(daoOrg);

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

}
