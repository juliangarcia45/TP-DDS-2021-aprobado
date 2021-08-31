package domain.autenticacion;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Administrador extends Usuario{
    //caracteristicas map es del config de caracteristicas a solicitar de las mascotas-Entrega 1
    public static Map<String, String> caracteristicasMap = new HashMap<String, String>();
    //preferenciasmap es del config del adoptante-Entrega 3
    public static Map<String, String> preferenciasMap = new HashMap<String, String>();

    public Administrador(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public void agregarCaracteristica (String caracteristica , String valor){ caracteristicasMap.put(caracteristica, valor);
    }

    public void agregarPreferencia (String preferencia , String valor){
        preferenciasMap.put(preferencia, valor);
    }

    public void agregarAlMapPreferencias() {
        FileOutputStream preferenciasConfig = null;
        try {
            preferenciasConfig = new FileOutputStream("src\\main\\java\\domain\\autenticacion\\configPreferenciasOrg.ser");
            agregarMapAlFile(preferenciasConfig, preferenciasMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarAlMapCaracteristicas() {
        FileOutputStream caracteristicasConfig = null;
        try {
            caracteristicasConfig = new FileOutputStream("src\\main\\java\\domain\\autenticacion\\configCaracteristicasOrg.ser");
            agregarMapAlFile(caracteristicasConfig, caracteristicasMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarMapAlFile(FileOutputStream organizacionConfig, Map<String, String> algunMap) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(organizacionConfig);
            out.writeObject(algunMap);
            out.close();
            organizacionConfig.close();
            System.out.printf("Map guardado en archivo correctamente!\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Map<String, String> leerMapPreferencias() {
        FileInputStream preferenciasIn= null;
        try{
            preferenciasIn = new FileInputStream("src\\main\\java\\domain\\autenticacion\\configPreferenciasOrg.ser");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return leerMapDelFile(preferenciasIn);
    }
    public Map<String, String> leerMapCaracteristicas() {
        FileInputStream caracteristicasIn= null;
        try{
            caracteristicasIn = new FileInputStream("src\\main\\java\\domain\\autenticacion\\configCaracteristicasOrg.ser");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return leerMapDelFile(caracteristicasIn);
    }
    public Map<String, String> leerMapDelFile(FileInputStream organizacionConfig) {
        Map<String, String> mapAux = null;
        try {
            ObjectInputStream in = new ObjectInputStream(organizacionConfig);
            mapAux = (Map<String, String>) in.readObject();
            in.close();
            organizacionConfig.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("error");
            c.printStackTrace();
            return null;
        }
        return mapAux;
    }
    public Map<String,String> getPreferenciasMap() {
        return preferenciasMap;
    }
    public Map<String,String> getCaracteristicasMap() {
        return caracteristicasMap;
    }

}
