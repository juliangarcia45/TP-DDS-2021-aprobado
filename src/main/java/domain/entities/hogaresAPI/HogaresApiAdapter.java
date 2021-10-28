package domain.entities.hogaresAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class HogaresApiAdapter {
    private static HogaresApiAdapter instancia = null;
    private static final String urlAPI = "https://api.refugiosdds.com.ar/api/";
    private Retrofit retrofit;

    private HogaresApiAdapter(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public static HogaresApiAdapter getInstance(){
        if (instancia == null){
            instancia = new HogaresApiAdapter();
        }
        return instancia;
    }

    public String getTokenApi() throws IOException {
        HogaresApiService hogaresApiService = this.retrofit.create(HogaresApiService.class);
        Call<String> requestToken = hogaresApiService.getToken("ignaciozullo@gmail.com");
        Response<String> responseToken = requestToken.execute();
        return responseToken.body();
    }
    public HogaresResponseApi getListaHogares (int pagina) throws IOException {
        HogaresApiService hogaresApiService = this.retrofit.create(HogaresApiService.class);
        Call<HogaresResponseApi> listaHogares = hogaresApiService.listaHogares(pagina);
        Response<HogaresResponseApi> responseList = listaHogares.execute();

        return responseList.body();
    }
}
