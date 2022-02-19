package server;

import domain.controllers.*;
import domain.middleware.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import Spark.utils.BooleanHelper;
import Spark.utils.HandlebarsTemplateEngineBuilder;

import static spark.Spark.before;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        UserRestController usuarioRestControllerEjemplo = new UserRestController();
        DuenioController duenioController = new DuenioController();
        LoginController loginController     = new LoginController();
        AuthMiddleware authMiddleware       = new AuthMiddleware();
        HomeController homeController       = new HomeController();
        AdminController adminController = new AdminController();
        PublicacionesAdopcionController publicacionesAdopcionController = new PublicacionesAdopcionController();

        Spark.get("/", loginController::inicio, Router.engine);
        Spark.get("/signIn", loginController::inicio,Router.engine);
        //Spark.before("/", authMiddleware::verificarSesion);

        Spark.post("/login", loginController::login);

        Spark.get("/logout", loginController::logout);

        Spark.get("/home", homeController::inicio, Router.engine);
        Spark.get("/signUp",loginController::signUp, Router.engine);
        Spark.post("/registrar",loginController::registrar);
        Spark.get("/registroMascota", duenioController::registroMascota, Router.engine);
        //Spark.post("/registrarMascota",usuarioController::registrarMascota); hay que probar
        Spark.get("/adopcion",publicacionesAdopcionController::adoptar, Router.engine);
        //Spark.before("/adopcion",publicacionesAdopcionController::verificarDuenio);

        Spark.get("/adminHome",adminController::adminHome,Router.engine);
        //Spark.before("/adminHome",publicacionesAdopcionController::verificarAdmin);
        Spark.get("/listadoOrganizaciones",adminController::listadoOrganizaciones,Router.engine);
        Spark.get("/agregarOrganizacion",adminController::addOrganizacion,Router.engine);
        Spark.post("/registrarOrganizacion",adminController::registrarOrganizacion);
        Spark.get("/editarOrganizacion/:id",adminController::editarOrganizacion,Router.engine);
        Spark.post("/actualizarOrganizacion",adminController::actualizarOrganizacion);
        Spark.delete("/borrarOrganizacion/:id",adminController::borrarOrganizacion,Router.engine);
        Spark.get("/verOrganizacion/:id", adminController::verOrganizacion,Router.engine);

        Spark.get("/listaUsuarios",adminController::listadoUsuarios,Router.engine);
        Spark.get("/listaVoluntarios",adminController::listadoVoluntarios,Router.engine);
        Spark.get("/listaPublicaciones",adminController::listadoPublicaciones,Router.engine);


        Spark.get("/usuarios/:id",(req,res)->req.queryParams("nombre"));

    }
}
