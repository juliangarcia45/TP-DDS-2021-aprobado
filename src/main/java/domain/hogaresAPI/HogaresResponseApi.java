package domain.hogaresAPI;

import com.google.gson.annotations.SerializedName;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.IOException;
import java.util.ArrayList;
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
        List<HogarDeTransito> hogaresDeTransito =new ArrayList<>();
        int totalPaginas;
        HogaresResponseApi json = hogaresApiAdapter.getListaHogares(1);
        totalPaginas= json.total/10;
        for(int i =1;i<=totalPaginas;i++){
             json = hogaresApiAdapter.getListaHogares(i);
             hogaresDeTransito.addAll(json.hogares);

        }
    }

}
