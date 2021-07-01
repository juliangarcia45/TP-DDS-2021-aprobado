package domain.organizacion;

import domain.autenticacion.Usuario;

public class Perdida implements EstadoMascota{
    private Mascota mascota;
    private Usuario personaACargo;

    public Perdida(Mascota mascota) {
        this.mascota = mascota;
    }
}
