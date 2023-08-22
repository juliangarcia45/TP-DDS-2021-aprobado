package domain.controllers;
import config.Config;
import domain.Repositorios.*;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.entities.PreguntasAdopcion.PreguntaAdopcion;
import domain.entities.PreguntasAdopcion.TipoPregunta;
import domain.entities.autenticacion.Usuario;
import domain.entities.fotos.Foto;
import domain.entities.fotos.Helper;
import domain.entities.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

public class DuenioController {
    private DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
    private RepositorioDeUsuarios repoUser = new RepositorioDeUsuarios(dao);

    private DAO<Duenio> daoD = new DAOHibernate<>(Duenio.class);
    private RepositorioDeDuenios repoDuenio = new RepositorioDeDuenios(daoD);

    private DAO<Foto> daoI = new DAOHibernate<>(Foto.class);
    private RepositorioImagenes repositorioImagenes= new RepositorioImagenes(daoI);

    private DAO<Organizacion> daoO = new DAOHibernate<>(Organizacion.class);
    private RepositorioDeOrganizaciones repoOrg = new RepositorioDeOrganizaciones(daoO);

    private DAO<Mascota> daoM = new DAOHibernate<>(Mascota.class);
    private RepositorioDeMascotas repoMascota = new RepositorioDeMascotas(daoM);

    boolean faltanDatosMascota;
    boolean faltaOrganizacion;

    public ModelAndView duenioHome(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("duenio",true);
        return new ModelAndView(parametros,"home.hbs");
    }

    public ModelAndView misMascotas(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        int idUsuarioEnSesion= request.session().attribute("id");
        Duenio duenioEnSesion= repoDuenio.buscar(idUsuarioEnSesion);
        parametros.put("duenioNombre",duenioEnSesion.getUsuario());
        parametros.put("mascotas",duenioEnSesion.getMascotas());
        return new ModelAndView(parametros,"duenio/misMascotas.hbs");
    }



    public ModelAndView registroMascota(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (faltanDatosMascota==true){
            parametros.put("faltanDatos",true);
            faltanDatosMascota=false;
        }
        return new ModelAndView(parametros,"duenio/registroMascota.hbs");
    }

    public Response registrarMascota(Request request, Response response) throws IOException, ServletException {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));


        int idUsuarioEnSesion= request.session().attribute("id");
        Duenio duenioEnSesion= (Duenio) repoUser.buscar(idUsuarioEnSesion);
        String nombreMascota = request.queryParams("nombre");
        String apodo     = request.queryParams("apodo");
        String descripcion = request.queryParams("descripcion");
        String sexo = request.queryParams("sexo");
        String especie =request.queryParams("especie");
        String edad = request.queryParams("edad");
        //hay que ver lo de las caracteristicas, esas cosas las toma del config del admin y creo que va a ser medio jodido porque la vista va a cambiar segun ese config

        //valido los datos
        if(nombreMascota.isEmpty()){
            faltanDatosMascota=true;
            response.status(400);
            response.redirect("/registroMascota");
            return response;
        }
        if(apodo.isEmpty()){
            faltanDatosMascota=true;
            response.status(400);
            response.redirect("/registroMascota");
            return response;
        }
        if(descripcion.isEmpty()){
            faltanDatosMascota=true;
            response.status(400);
            response.redirect("/registroMascota");
            return response;
        }
        if(sexo.isEmpty()){
            faltanDatosMascota=true;
            response.status(400);
            response.redirect("/registroMascota");
            return response;
        }
        if(edad.isEmpty()){
            faltanDatosMascota=true;
            response.status(400);
            response.redirect("/registroMascota");
            return response;
        }

        Mascota mascota= new Mascota(nombreMascota,sexo,apodo,descripcion,Integer.parseInt(edad),especie,null,null,null,null);
        //fotos

        List<String> listaFotos = Helper.processImage(request,"fotosMascota", Config.RUTA_IMAGENES_FRONT);//Content-Type != multipart/form-data

        List<Foto> imagenes=new ArrayList<>();

        for (String image: listaFotos) {
            Foto foto = new Foto(image);
            this.repositorioImagenes.agregar(foto);
            foto.normalizar();
            imagenes.add(foto);
            //mascota.getFotos().add(foto);
        }
        mascota.setFotos(imagenes);
        duenioEnSesion.registrarMascota(mascota);
        repoMascota.agregar(mascota);
        response.status(200);
        response.redirect("/duenio/misMascotas");
        return response;
    }

    public ModelAndView elegirOrganizacionParaAdopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (faltaOrganizacion==true){
            parametros.put("faltanDatos",true);
            faltaOrganizacion=false;
        }
        parametros.put("organizacion",repoOrg.buscarTodasLasOrganizaciones());
        return new ModelAndView(parametros,"duenio/elegirOrganizacion.hbs");
    }

    public Response eleccionOrganizacion(Request request, Response response){
        String idOrganizacion= request.queryParams("Organizacion");
        if(idOrganizacion.isEmpty()){
            faltaOrganizacion=true;
            response.redirect("/duenio/elegirOrganizacion");
            return response;
        }
        response.redirect("/duenio/formularioAdopcion/"+idOrganizacion);
        return response;
    }

    public ModelAndView formularioAdopcion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        Integer id=new Integer(request.params("id"));
        Organizacion organizacion=repoOrg.buscar(id);
        int idUsuarioEnSesion= request.session().attribute("id");
        Duenio duenioEnSesion= (Duenio) repoUser.buscar(idUsuarioEnSesion);
        if(!organizacion.getPreguntas().isEmpty()){
            List<PreguntaAdopcion> preguntasSimples= (List<PreguntaAdopcion>) organizacion.getPreguntas().stream().filter(preguntaAdopcion -> preguntaAdopcion.getTipo().equals(TipoPregunta.SINGLE));
            List<PreguntaAdopcion> preguntasMultiples= (List<PreguntaAdopcion>) organizacion.getPreguntas().stream().filter(preguntaAdopcion -> preguntaAdopcion.getTipo().equals(TipoPregunta.MULTIPLE));
            parametros.put("preguntaSimple", preguntasSimples);
            parametros.put("preguntaMultiple", preguntasMultiples);
        }
        parametros.put("mascota", duenioEnSesion.getMascotas() );
        return new ModelAndView(parametros, "duenio/formularioAdopcion.hbs");
    }

    /*public Response generarPublicacionAdopcion(Request request,Response response){
        int idUsuarioEnSesion= request.session().attribute("id");
        Duenio duenioEnSesion= (Duenio) repoUser.buscar(idUsuarioEnSesion);
        String idMascota=request.queryParams("mascota");
        Mascota mascota=repoMascota.buscar(Integer.parseInt(idMascota));
        List<String> respuestasSimples

    }*/

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

    /*public Response suscribirseARecomendaciones(Request request, Response response){
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
        }*/
    }
