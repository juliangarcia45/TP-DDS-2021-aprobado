package domain;

import org.junit.Before;
import org.junit.Test;

import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.Mascota;

import domain.entities.notificacion.*;


import java.io.File;
import java.util.Arrays;
import java.util.List;
public class TestQRCode{
    Mascota mascota;
    Duenio duenio;
    Contacto contacto;
    @Before
    public void registrarMediosNotificacion(){
        MedioDeNotificacion whatsapp = new MedioDeNotificacion();
        whatsapp.setEstrategiaNotificacion(new WhatsApp());
        MedioDeNotificacion sms = new MedioDeNotificacion();
        sms.setEstrategiaNotificacion(new Sms());

     
        List<MedioDeNotificacion> mediosDeNotificacion =Arrays.asList(whatsapp,sms);
        Contacto contactoDuenio = new Contacto("Jorgito","jJorgitoApellido",
                "jorgito@gmail.com",1156956024,mediosDeNotificacion);

       this.duenio = new Duenio("jorge", "jorge123");
       List<Contacto> listaContactosDuenio = Arrays.asList(contactoDuenio);
       this.duenio.setMediosDeContacto(listaContactosDuenio);
       mascota = new Mascota.MascotaBuilder(null,null).nombre("gato").build();
    
        this.duenio.registrarMascota(mascota);
        this.duenio.setNombre("jorgito");
        String nombre = this.mascota.getDuenio().getNombre();
        System.out.println(nombre);
        this.contacto = this.duenio.getMediosDeContacto().get(0);
    }

    
    @Test
    public void generateQR() {
        File f = mascota.generateQR();
        mascota.leerQr(f);
    }

    @Test
    public void leerQr(){
        File f = new File("qrCode.png");
        mascota.leerQr(f);
    }


}