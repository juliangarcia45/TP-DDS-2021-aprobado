package domain.entities.autenticacion.validadorDeContrasenias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ContraseniasInvalidas implements Requisito{
    public boolean validar(String contrasenia) {
        File archivo = null;
        FileReader lectorCaracter = null; // lee caracter a caracter
        BufferedReader lectorPalabra = null; // lee una palabra al detectar un \n

        try {
            archivo = new File("src/main/java/domain/autenticacion/validadorDeContrasenias/Contrasenias10000.txt");
            lectorCaracter = new FileReader (archivo);
            lectorPalabra = new BufferedReader(lectorCaracter);
            String linea;
            while((linea=lectorPalabra.readLine())!=null)// mientras no lleguemos al final del archivo
                if(linea.contentEquals(contrasenia))
                {
                    return !(linea.contentEquals(contrasenia));
                }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // cierra el archivo
            try{
                if( lectorCaracter !=  null ){
                    lectorCaracter.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return true;
    }
}
