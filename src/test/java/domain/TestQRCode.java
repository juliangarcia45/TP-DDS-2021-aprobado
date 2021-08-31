package domain;

import org.junit.Before;
import org.junit.Test;

import domain.generadorQR.*;
import domain.autenticacion.Usuario;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;

import domain.notificacion.*;


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

        this.mascota = new Mascota("gato",true,null,null,null,true,null,duenio,null);
        this.duenio.registrarMascota(mascota);
        this.duenio.setNombre("jorgito");
        String nombre = this.mascota.getDuenio().getNombre();
        System.out.println(nombre);
        this.contacto = this.duenio.getMediosDeContacto().get(0);
    }

    
    @Test
    public void generateQR() {
        GeneradorQR qr = new GeneradorQR();
        File f = new File("qrCode.png");
        String text = contacto.getContacto();
    

        try {

            qr.generateQR(f, text, 300, 300);
            System.out.println("QRCode Generated: " + f.getAbsolutePath());

            String qrString = qr.decoder(f);
            System.out.println("Text QRCode: " + qrString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}