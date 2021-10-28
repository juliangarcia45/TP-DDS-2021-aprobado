package domain.entities.autenticacion.validadorDeContrasenias;

public class CaracteresConsecutivos implements Requisito{
    public boolean validar(String contrasenia){
        int i=0;
        int caracter1=0;
        int caracter2=0;
        int caracter3=0;
        for(i=1;i<contrasenia.length()-1;i++){
            caracter1 = contrasenia.charAt(i-1);
            caracter2 = contrasenia.charAt(i);
            caracter3 = contrasenia.charAt(i+1);
            if(caracter2 - 1 == caracter1 && caracter2 +1 == caracter3)
                return false;
        }
        return true;
    }
}
