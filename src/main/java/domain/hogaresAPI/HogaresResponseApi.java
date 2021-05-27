package domain.hogaresAPI;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;

public class HogaresResponseApi {
    @SerializedName("total")
    private Integer total;
    @SerializedName("offset")
    private Integer offset;
    @SerializedName("hogares")
    private List<HogarDeTransito> hogares;


    public static void main(String[] args) throws IOException {
        HogaresApiAdapter hogaresApiAdapter = HogaresApiAdapter.getInstance();
        HogaresResponseApi json = hogaresApiAdapter.getListaHogares();
        json.hogares.forEach(hogarDeTransito -> System.out.println(hogarDeTransito.getNombre()));
    }

}
