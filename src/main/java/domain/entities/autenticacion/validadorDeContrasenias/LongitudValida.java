package domain.entities.autenticacion.validadorDeContrasenias;

public class LongitudValida implements Requisito{
    public boolean validar(String contrasenia){
        return contrasenia.length() >= 8;

    }
}
