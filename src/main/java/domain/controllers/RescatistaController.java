package domain.controllers;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeMascotas;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.RepositorioImagenes;
import domain.entities.autenticacion.Usuario;
import domain.entities.fotos.Foto;
import domain.entities.fotos.Helper;
import domain.entities.organizacion.*;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RescatistaController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    private DAO<Foto> daoI = new DAOHibernate<>(Foto.class);
    private RepositorioImagenes repositorioImagenes= new RepositorioImagenes(daoI);

    private DAO<Mascota> daoM = new DAOHibernate<>(Mascota.class);
    private RepositorioDeMascotas repoMascota = new RepositorioDeMascotas(daoM);

    private DAO<Publicacion> daoP = new DAOHibernate<>(Publicacion.class);
    private RepositorioDePublicaciones repoPublicaciones = new RepositorioDePublicaciones(daoP);

    public Response registrarMascotaPerdida(Request request, Response response){ //Esto seria sin chapita
        try{
            String idUsuarioEnSesion= request.session().attribute("id");
            Rescatista rescatistaEnSesion= (Rescatista) repoUser.buscar(Integer.parseInt(idUsuarioEnSesion));
            String latitudEncMascota = request.queryParams("latitud encuentro mascota");
            String longitudEncMascota = request.queryParams("longitud encuentro mascota");
            String estadoMascota = request.queryParams("descripcion estado mascota");
            List<String> listaFotos = Helper.processImage(request,"fotos_mascota", Config.RUTA_IMAGENES_FRONT);
            Ubicacion encuentroMascota= new Ubicacion();
            encuentroMascota.setLatitud(Double.parseDouble(latitudEncMascota));
            encuentroMascota.setLongitud(Double.parseDouble(longitudEncMascota));
            rescatistaEnSesion.setDireccionEncuentroMascota(encuentroMascota);

            //fotos
            for (String image: listaFotos) {
                Foto foto = new Foto(image);
                this.repositorioImagenes.agregar(foto);
                foto.normalizar();
                rescatistaEnSesion.setFotoMascota(foto);
            }

            repoUser.modificar(rescatistaEnSesion);

            //hay que ver lo de las caracteristicas, esas cosas las toma del config del admin y creo que va a ser medio jodido porque la vista va a cambiar segun ese config


            //if(!repoPublicaciones.existePublicacionPorFoto(listaFotos.get(0))){ //hay que ver para que recorra todas las fotos de la lista
            //    GestorPublicaciones.generarPublicacionMascotaPerdida(rescatistaEnSesion,estadoMascota,listaFotos);
             //   response.status(200);
            //}
            //else{
            //    response.status(404);
            //    response.redirect("/");
            //}

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
}
