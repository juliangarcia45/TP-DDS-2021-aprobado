package domain.organizacion;

public class MascotaEnAdopcion extends EstadoMascota {
    public MascotaEnAdopcion(){}

    @Override
    public void mostrarEstado() {
        System.out.println("Mascota en adopcion");
    }
}
