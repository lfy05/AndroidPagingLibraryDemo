package dev.feiyang.paginglibrarytry;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofitClient;
    private static String BASE_URL = "https://mistplay-challenge.firebaseio.com/";

    public static GameDataService getService (){
        if (retrofitClient == null){
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitClient.create(GameDataService.class);
    }
}
