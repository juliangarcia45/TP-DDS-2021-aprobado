package domain;

import domain.entities.hogaresAPI.HogarDeTransito;
import domain.entities.hogaresAPI.HogaresResponseApi;
import domain.entities.organizacion.Rescatista;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class buscarHogaresDeTransitoTest {
    Rescatista rescatista;
    HogaresResponseApi hogaresResponseApi;
    @Before
    public void init()  {
        rescatista = new Rescatista("rescatista","contraseniaRescatista");
        hogaresResponseApi = new HogaresResponseApi();
        rescatista.setHogaresResponseApi(hogaresResponseApi);
    }
    @Test
    public void buscarHogares() throws IOException {
        List<HogarDeTransito> listaHogares =rescatista.buscarHogarDeTransito("Latitud","Longitd");

    }
}
