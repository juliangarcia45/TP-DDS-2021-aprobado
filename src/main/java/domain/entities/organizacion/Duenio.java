package domain.entities.organizacion;

import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.autenticacion.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="duenio")
@PrimaryKeyJoinColumn(name="id")
public class Duenio extends Usuario {

    @OneToMany(mappedBy = "duenio",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Mascota> mascotas = new ArrayList<Mascota>();

    public Duenio(String usuario, String contrasenia) {
        super(usuario, contrasenia);
        setTipoUsuario(TipoUsuario.DUENIO);
    }
    
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void registrarMascota(Mascota mascota){
        this.mascotas.add(mascota);
        mascota.setDuenio(this);
    }

    //Mascota perdida
    public List<Publicacion> buscarMascotaPerdida(){
        return GestorPublicaciones.obtenerPublicacionesMascPerdidas();
    }

    //Mascota adoptada
//VER ACA
    public List<Publicacion> buscarMascotasEnAdopcion(){
       return GestorPublicaciones.obtenerPublicacionesMascEnAdopcion();
    }
    
    public void darEnAdopcion(Mascota mascota, List<Organizacion> organizaciones){
        organizaciones.stream().forEach(organizacion->GestorPublicaciones.generarPublicacionMascotaEnAdopcion(this,mascota, organizacion));
        this.getMascotas().remove(mascota);
        mascota.setDuenio(null);
    }

    //Duenio interesado en adoptar
    public void suscribirseARecomendacionesDeAdopcion(List<RespuestaAdopcion> preferencias){
        GestorAdoptantes.agregarInteresado(new PublicacionAdoptante(this,preferencias));
    }

    public void desuscribirseARecomendacionesDeAdopcion(){
            GestorAdoptantes.removerPorId(GestorUsuarios.obtenerUsuario(this.getUsuario()).getId());
        }

    public Duenio() {
    }
}


