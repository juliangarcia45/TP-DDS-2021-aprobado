package domain.entities.autenticacion;


import domain.entities.organizacion.Organizacion;
import domain.entities.organizacion.TipoUsuario;

import javax.persistence.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="administrador")
@PrimaryKeyJoinColumn(name="id")
public class Administrador extends Usuario{

    @ManyToOne
    @JoinColumn(name="organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    @Transient
    public static Map<String, String> nombreMap = new HashMap<String, String>();

    public Administrador(String usuario, String contrasenia) {
        super(usuario, contrasenia);
        setTipoUsuario(TipoUsuario.ADMINISTRADOR);
    }

    public void agregarCaracteristica (String caracteristica , String valor){
        nombreMap.put(caracteristica, valor);
    }

    public void agregarMapAlFile(FileOutputStream organizacionConfig){
        try {
            organizacionConfig = new FileOutputStream("src\\main\\java\\domain\\autenticacion\\configOrganizacion.ser");
            ObjectOutputStream out = new ObjectOutputStream(organizacionConfig);
            out.writeObject(nombreMap);
            out.close();
            organizacionConfig.close();
            System.out.printf("Map guardado en configOrganizacion.ser correctamente!\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }public Map<String, String> leerMapDelFile(FileOutputStream organizacionConfig) {
        Map<String, String> mapAux = null;
        try {
            FileInputStream fileIn = new FileInputStream("src\\main\\java\\domain\\autenticacion\\configOrganizacion.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            mapAux = (Map<String, String>) in.readObject();
            in.close();
            fileIn.close();
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
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Administrador() {
    }
}