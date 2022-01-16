package domain.controllers;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeMascotas;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.autenticacion.Usuario;
import domain.Repositorios.Repositorio;
import domain.Repositorios.factories.FactoryRepositorio;
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
                duenioEnSesion.registrarMascota(korone);
                repoUser.modificar(duenioEnSesion);
                //ACA EL Duenio SERIA EL QUE ESTA EN SU SESION REGISTRANDO A LA MASCOTA, NO SE COMO HABRIA QUE HACER PARA OBTENERLO
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
            String idUsuarioEnSesion= request.session().attribute("id");
            Duenio autor=(Duenio) repoUser.buscar(Integer.parseInt(idUsuarioEnSesion));
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
