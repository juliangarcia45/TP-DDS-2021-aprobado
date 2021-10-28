package server;

import domain.controllers.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import Spark.utils.BooleanHelper;
import Spark.utils.HandlebarsTemplateEngineBuilder;

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
        LoginController loginController=new LoginController();
        Spark.get("/log-in", loginController::inicio, Router.engine);
        Spark.post("/log-in", loginController::guardarUsuario);


    }
}
