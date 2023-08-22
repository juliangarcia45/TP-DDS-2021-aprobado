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
        VoluntarioController voluntarioController = new VoluntarioController();

        Spark.get("/", loginController::inicio, Router.engine);
        Spark.get("/signIn", loginController::inicio,Router.engine);
        //Spark.before("/", authMiddleware::verificarSesion);

        Spark.post("/login", loginController::login);

        Spark.get("/logout", loginController::logout);

        Spark.get("/home", homeController::inicio, Router.engine);
        Spark.get("/signUp",loginController::signUp, Router.engine);
        Spark.post("/registrar",loginController::registrar);

        Spark.get("/duenio/home", duenioController::duenioHome, Router.engine);

        Spark.get("/duenio/misMascotas", duenioController::misMascotas,Router.engine);;
        Spark.get("/registroMascota", duenioController::registroMascota, Router.engine);
        Spark.post("/duenio/registrarMascota", duenioController::registrarMascota);
        Spark.get("/duenio/elegirOrgAdopcion",duenioController::elegirOrganizacionParaAdopcion,Router.engine);
        Spark.post("/duenio/eleccionOrg",duenioController::eleccionOrganizacion);
        Spark.get("/duenio/formularioAdopcion/:id",duenioController::formularioAdopcion,Router.engine);
        Spark.get("/adopcion",publicacionesAdopcionController::adoptar, Router.engine);
        //Spark.before("/adopcion",publicacionesAdopcionController::verificarDuenio);

        //ADMIN
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
        Spark.get("/agregarUsuario",adminController::agregarUsuario,Router.engine);
        Spark.post("/admin/registrarUsuario",adminController::registrarUsuario);
        Spark.get("/editarUsuario/:id",adminController::editarUsuario,Router.engine);
        Spark.post("/admin/actualizarUsuario",adminController::actualizarUsuario);
        Spark.get("/verUsuario/:id",adminController::verUsuario,Router.engine);
        Spark.delete("/borrarUsuario/:id",adminController::borrarUsuario,Router.engine);

        //VOLUNTARIO
        Spark.get("/voluntarioHome", voluntarioController::voluntarioHome,Router.engine);

        Spark.get("/listaVoluntarios", voluntarioController::listadoVoluntarios,Router.engine);
        Spark.get("/agregarVoluntario",voluntarioController::agregarVoluntario,Router.engine);
        Spark.post("/voluntario/registrarVoluntario",voluntarioController::registrarVoluntario);
        Spark.get("/editarVoluntario/:id",voluntarioController::editarVoluntario,Router.engine);
        Spark.post("/voluntario/actualizarVoluntario",voluntarioController::actualizarVoluntario);
        Spark.get("/verVoluntario/:id",voluntarioController::verVoluntario,Router.engine);
        Spark.delete("/borrarVoluntario/:id",voluntarioController::borrarVoluntario,Router.engine);

        Spark.get("/listaPublicacionesAdopcion", voluntarioController::listadoPublicacionesAdopcion,Router.engine);
        Spark.get("/verPublicacionAdopcion/:id", voluntarioController::verPublicacionAdopcion,Router.engine);
        Spark.post("/aprobarPublicacionAdopcion",voluntarioController::aprobarPublicacionAdopcion);
        Spark.delete("/borrarPublicacionAdopcion/:id",voluntarioController::borrarPublicacionAdopcion,Router.engine);

        Spark.get("/listaPublicacionesPerdidas", voluntarioController::listadoPublicacionesPerdidas,Router.engine);
        Spark.get("/verPublicacionPerdida/:id", voluntarioController::verPublicacionPerdida,Router.engine);
        Spark.post("/aprobarPublicacionPerdida",voluntarioController::aprobarPublicacionPerdida);
        Spark.delete("/borrarPublicacionPerdida/:id",voluntarioController::borrarPublicacionPerdida,Router.engine);

        Spark.get("/voluntario/listaPreguntas", voluntarioController::preguntasDeLaOrganizacion,Router.engine);
        Spark.get("/voluntario/agregarPregunta", voluntarioController::agregarPreguntaALaOrganizacion,Router.engine);
        Spark.post("/agregarPreguntaAOrganizacion",voluntarioController::agregarPregunta);
        Spark.get("/verPregunta/:id",voluntarioController::verPregunta,Router.engine);
        Spark.delete("voluntario/borrarPregunta/:id",voluntarioController::borrarPregunta,Router.engine);





        Spark.get("/listaVoluntarios",adminController::listadoVoluntarios,Router.engine);
        Spark.get("/listaPublicaciones",adminController::listadoPublicaciones,Router.engine);


        Spark.get("/usuarios/:id",(req,res)->req.queryParams("nombre"));

    }
}
