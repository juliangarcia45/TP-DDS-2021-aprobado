package domain.controllers;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.Repositorios.RepositorioDePublicacionesEnAdopcion;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.entities.autenticacion.Usuario;
import domain.entities.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionesAdopcionController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);
    private DAO<PublicacionMascotaEnAdopcion> daoP = new DAOHibernate<>(PublicacionMascotaEnAdopcion.class);
    private RepositorioDePublicacionesEnAdopcion repoPublicaciones = new RepositorioDePublicacionesEnAdopcion(daoP);

    public ModelAndView adoptar(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        List<PublicacionMascotaEnAdopcion> publicaciones = this.repoPublicaciones.buscarPorEstado(EstadoPublicacion.APROBADO);
        parametros.put("publicaciones", publicaciones);
        return new ModelAndView(parametros,"adopcionMasotas.hbs");
    }

    public Response verificarDuenio(Request request, Response response){
        int idUsuarioEnSesion= request.session().attribute("id");
        Usuario duenioEnSesion= repoUser.buscar(idUsuarioEnSesion);
        if (duenioEnSesion.getTipoUsuario()== TipoUsuario.DUENIO){response.status(200);}
        else {response.status(406);
            response.redirect("/home");}
        return response;
    }

    /*public Response contactarDuenio(Request request, Response response){
        try{
            int idPublicacion=request.attribute("id"); Hay que ver como obtener el id de una publicacion
            PublicacionMascotaEnAdopcion publicacionEscogida=(PublicacionMascotaEnAdopcion) repoPublicaciones.buscar(idPublicacion);
            int idUsuarioEnSesion= request.session().attribute("id");
            Usuario duenioEnSesion= repoUser.buscar(idUsuarioEnSesion);
            List<Contacto> contactos=duenioEnSesion.getMediosDeContacto();
            publicacionEscogida.quieroAdoptar(contactos.get(0).getContacto());
            response.status(202);
        }
         catch (Exception e){
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.status(406);
            response.redirect("/");
        }
        finally {
            return response;
        }
    }

     */


}
