package domain.entities.autenticacion.validadorDeContrasenias;

public class CaracteresValidos implements Requisito{
    static String listaCararcteresValidos = " !\",#$%&()*+-,-./0123456789:;<=>ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`@abcdefghijklmnopqrstuvwxyz{}";
    public boolean validar(String contrasenia){
        int i=0;
        for(i=0;i<contrasenia.length();i++){
            if(!listaCararcteresValidos.contains(Character.toString(contrasenia.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
