package br.ifsul.yesorno;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {
    @GET("api/")
    Call<YON> consultarYESORNO();

}
