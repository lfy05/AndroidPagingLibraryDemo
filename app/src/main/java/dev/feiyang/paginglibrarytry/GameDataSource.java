package dev.feiyang.paginglibrarytry;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PositionalDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GameDataSource extends PositionalDataSource<Game> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Game> callback) {
        final List<Game> games = new ArrayList<>();
        for(int i = 0; i < params.requestedLoadSize; i++){
            Call<Game> request = RetrofitClient.getService().getGame(i + params.requestedStartPosition);
            int finalI = i;
            try {
                Response response = request.execute();
                games.add((Game) response.body());
                if (finalI == params.requestedLoadSize - 1){
                    // TOTAL COUNT IS IMPORTANT! IT IS NOT THE TOTAL COUNT OF THE CURRENT FETCHED, BUT THE TOTAL COUNT OF POTENTIALLY POSSIBLY FETCHED
                    Log.d("Retrofit", "Added " + finalI + " records");
                    callback.onResult(games, 0, 600);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Game> callback) {
        final List<Game> games = new ArrayList<>();
        for(int i = 0; i < params.loadSize; i++){
            Call<Game> request = RetrofitClient.getService().getGame(i + params.startPosition);
            int finalI = i;
            try {
                Response response = request.execute();
                games.add((Game) response.body());
                if (finalI == params.loadSize - 1){
                    // TOTAL COUNT IS IMPORTANT! IT IS NOT THE TOTAL COUNT OF THE CURRENT FETCHED, BUT THE TOTAL COUNT OF POTENTIALLY POSSIBLY FETCHED
                    Log.d("Retrofit", "Added " + finalI + " records");
                    callback.onResult(games);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
