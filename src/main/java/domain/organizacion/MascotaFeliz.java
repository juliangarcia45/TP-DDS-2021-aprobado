package domain.organizacion;

public class MascotaFeliz extends EstadoMascota{
    public MascotaFeliz(){}

    @Override
    public void mostrarEstado() {
        System.out.println("Mascota feliz");
    }
}
