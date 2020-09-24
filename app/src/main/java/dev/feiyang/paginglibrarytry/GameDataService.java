package dev.feiyang.paginglibrarytry;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GameDataService {
    @GET("{index}.json")
    Call<Game> getGame(@Path("index") int index);
}
