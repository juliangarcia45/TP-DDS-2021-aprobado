package domain.controllers;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeMascotas;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.entities.autenticacion.Usuario;
import domain.Repositorios.Repositorio;
import domain.Repositorios.factories.FactoryRepositorio;
import domain.entities.notificacion.*;
import domain.entities.organizacion.Documento;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.Mascota;
import domain.entities.organizacion.TipoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class UsuarioController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    private DAO<Mascota> daoM = new DAOHibernate<>(Mascota.class);
    private RepositorioDeMascotas repoMascota = new RepositorioDeMascotas(daoM);

    public Response registrarMascota(Request request, Response response){
        try{
            String nombreMascota = request.queryParams("nombre");
            String apodo     = request.queryParams("apodo");
            String descripcion = request.queryParams("descripcion");
            String fotos = request.queryParams("fotos");
            String sexo = request.queryParams("sexo");
            String especie =request.queryParams("especie");
            String edad = request.queryParams("edad");
            //hay que ver lo de las caracteristicas, esas cosas las toma del config del admin y creo que va a ser medio jodido porque la vista va a cambiar segun ese config


            if(!repoMascota.existe(nombreMascota)){
                List<String> listaFotos = new ArrayList<>();
                listaFotos = Arrays.asList(fotos.split(","));
                //ACA ESTOY ASUMIENDO QUE EL QUERYPARAMS VA A AGARRAR TODOS LOS PATHS DE LAS FOTOS QUE LE CARGUES Y LOS VA A TENER SEPARADOS POR UNA ,
                //EJ: gato1.jpg , gato2.jpg, etc
                //Igual creo que en la vista solo acepta una imagen xd
                Mascota korone= new Mascota.MascotaBuilder(listaFotos,descripcion).nombre(nombreMascota).apodo(apodo).edad(Integer.parseInt(edad)).sexo(sexo).especie(especie).build();
                /*Duenio.registrarMascota(korone);
                repoUser.modificar(Duenio);
                ACA EL Duenio SERIA EL QUE ESTA EN SU SESION REGISTRANDO A LA MASCOTA, NO SE COMO HABRIA QUE HACER PARA OBTENERLO*/
                response.status(200);
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
        finally {
            return response;
        }
    }


    
    
}
