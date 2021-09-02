package domain.organizacion;

public class MascotaPerdida extends EstadoMascota {
    public MascotaPerdida(){}

    @Override
    public void mostrarEstado() {
        System.out.println("Mascota perdida");
    }

}
/*public class Perdida implements EstadoMascota{
    private Mascota mascota;
    private Usuario personaACargo;

    public Perdida(Mascota mascota) {
        this.mascota = mascota;
    }

 */