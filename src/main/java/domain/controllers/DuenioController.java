package domain.controllers;
import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeMascotas;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.Repositorios.RepositorioImagenes;
import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.autenticacion.Usuario;
import domain.Repositorios.Repositorio;
import domain.Repositorios.factories.FactoryRepositorio;
import domain.entities.fotos.Foto;
import domain.entities.fotos.Helper;
import domain.entities.notificacion.*;
import domain.entities.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class DuenioController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    private DAO<Foto> daoI = new DAOHibernate<>(Foto.class);
    private RepositorioImagenes repositorioImagenes= new RepositorioImagenes(daoI);

    private DAO<Mascota> daoM = new DAOHibernate<>(Mascota.class);
    private RepositorioDeMascotas repoMascota = new RepositorioDeMascotas(daoM);

    public ModelAndView registroMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"registroMascota.hbs");
    }

    public Response registrarMascota(Request request, Response response){
        try{
            String idUsuarioEnSesion= request.session().attribute("id");
            Duenio duenioEnSesion= (Duenio) repoUser.buscar(Integer.parseInt(idUsuarioEnSesion));
            String nombreMascota = request.queryParams("nombre");
            String apodo     = request.queryParams("apodo");
            String descripcion = request.queryParams("descripcion");
            List<String> listaFotos = Helper.processImage(request,"fotos_mascota", Config.RUTA_IMAGENES_FRONT);
            String sexo = request.queryParams("sexo");
            String especie =request.queryParams("especie");
            String edad = request.queryParams("edad");
            //hay que ver lo de las caracteristicas, esas cosas las toma del config del admin y creo que va a ser medio jodido porque la vista va a cambiar segun ese config


            if(!repoMascota.existe(nombreMascota)){
                Mascota korone= new Mascota(nombreMascota,sexo,apodo,descripcion,Integer.parseInt(edad),especie,null,null,null,null);
                //fotos
                for (String image: listaFotos) {
                    Foto foto = new Foto(image);
                    this.repositorioImagenes.agregar(foto);
                    foto.normalizar();
                    korone.setFoto(foto);
                }
                duenioEnSesion.registrarMascota(korone);
                repoUser.modificar(duenioEnSesion);
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

    public Response darMascotaEnAdopcion(Request request, Response response){
        try{
            String idUsuarioEnSesion= request.session().attribute("id");
            Duenio autor=(Duenio) repoUser.buscar(Integer.parseInt(idUsuarioEnSesion));
            String nombreMascota= request.queryParams("mascota");
            List<Mascota> mascotas=new ArrayList<>();
            mascotas=autor.getMascotas();
            mascotas.stream().filter(mascota -> mascota.getNombre()==nombreMascota);
            //GestorPublicaciones.generarPublicacionMascotaEnAdopcion(autor,mascotas.get(0),organizacion);
            //no se como le pasariamos la organizacion
            response.status(200);
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

    public Response suscribirseARecomendaciones(Request request, Response response){
        try{
            int idUsuarioEnSesion= request.session().attribute("id");
            Duenio autor=(Duenio) repoUser.buscar(idUsuarioEnSesion);
            String respuestaPreferenciaUno= request.queryParams("preferencia1");
            String respuestaPreferenciaDos= request.queryParams("preferencia2");
            String respuestaPreferenciaTres= request.queryParams("preferencia3");
            //vamos a limitar las preferencias a 3 para no complicarnos, en la pantalla tendria que mostrar 3 preguntas que selecciono el admin para la organizacion
            //solo nos importan los valores que elige el duenio para guardar las respuestas
            RespuestaAdopcion preferenciaUno=new RespuestaAdopcion();
            RespuestaAdopcion preferenciaDos=new RespuestaAdopcion();
            RespuestaAdopcion preferenciaTres=new RespuestaAdopcion();
            preferenciaUno.agregarValor(respuestaPreferenciaUno);
            preferenciaDos.agregarValor(respuestaPreferenciaDos);
            preferenciaTres.agregarValor(respuestaPreferenciaTres);
            //aca hay que ver porque hay preguntas que aceptan multiples respuestas(como por ej. de que color preferis el animal, podrias elegir blanco y marron) no se como
            //lo trae la query en string, pero seguro hay que hacer una separacion para separar los valores
            List<RespuestaAdopcion> preferencias=new ArrayList<>();
            preferencias.add(preferenciaUno);
            preferencias.add(preferenciaDos);
            preferencias.add(preferenciaTres);
            PublicacionAdoptante interesado=new PublicacionAdoptante(autor,preferencias);
            GestorAdoptantes.agregarInteresado(interesado);
            response.status(200);
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
