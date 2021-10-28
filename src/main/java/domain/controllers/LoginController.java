package domain.controllers;

import domain.Repositorios.Repositorio;
import domain.Repositorios.factories.FactoryRepositorio;
import domain.entities.autenticacion.Usuario;
import domain.entities.organizacion.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Repositorio<Usuario> repo;

    public LoginController(){
        this.repo = FactoryRepositorio.get(Usuario.class);
    }
    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }

    private void asignarAtributosA(Usuario usuario, Request request) {
        if (request.queryParams("usuario") != null) {
            usuario.setUsuario(request.queryParams("usuario"));
        }

        if (request.queryParams("contrasenia") != null) {
            usuario.setNombre(request.queryParams("contrasenia"));
        }
    }

    public Response guardarUsuario(Request request, Response response){
        Usuario usuario= new Duenio();
        asignarAtributosA(usuario,request);
        this.repo.agregar(usuario);
        response.redirect("/log-in");
        return response;


    }
}
