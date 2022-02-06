package domain.publicaciones;


import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.entities.autenticacion.Usuario;
import domain.entities.organizacion.*;
import org.junit.Test;



public class cargarPublicacion {
    private static DAO<Publicacion> dao= new DAOHibernate<>(Publicacion.class);
    private static RepositorioDePublicaciones repo=new RepositorioDePublicaciones(dao);
    private static DAO<Usuario> daoU= new DAOHibernate<>(Usuario.class);
    private static RepositorioDeUsuarios repoU=new RepositorioDeUsuarios(daoU);
    String nombre;
    String descripcionM;
    String descripcion;
    int edad;
    Duenio clark=new Duenio();


    @Test
    public void initPublicaciones() {
        clark= (Duenio) repoU.buscarUsuarioExistente("nathaas","contrasenia");
        this.nombre="calliope";
        this.descripcion="sexodsfsd";
        this.descripcionM="gato";
        this.edad=14;
        Mascota calli=new Mascota();
        calli.setNombre(nombre);
        calli.setDescripcion(descripcionM);
        calli.setEdad(edad);
        clark.registrarMascota(calli);
        Publicacion publicacion=new PublicacionMascotaEnAdopcion(clark,calli);
        publicacion.setEstado(EstadoPublicacion.APROBADO);
        publicacion.setDescripcion(descripcion);
        repo.agregarPublicacion(publicacion);

    }
}
