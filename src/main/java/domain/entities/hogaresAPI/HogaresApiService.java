package domain.entities.hogaresAPI;

import retrofit2.Call;
import retrofit2.http.*;

public interface HogaresApiService {
     String token = "xBOdLcFoihrx3MtrphGp7MVfz4LCyd4ac9D9Z4SokfGvQ5nh9k3Z9Q83ipHA";

    @POST("usuarios")
    Call<String> getToken(@Body String email);

    @Headers({"Accept: application/json",
                "Authorization: Bearer xBOdLcFoihrx3MtrphGp7MVfz4LCyd4ac9D9Z4SokfGvQ5nh9k3Z9Q83ipHA"})
    @GET("hogares")
    Call<HogaresResponseApi> listaHogares(@Query("offset") int page);
}