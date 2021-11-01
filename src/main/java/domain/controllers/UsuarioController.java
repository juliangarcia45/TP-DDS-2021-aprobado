package domain.controllers;
import domain.entities.autenticacion.Usuario;
import domain.Repositorios.Repositorio;
import domain.Repositorios.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {
    private Repositorio<Usuario> repo;

    public UsuarioController(){
        this.repo = FactoryRepositorio.get(Usuario.class);
    }
    
    
}
