package dev.feiyang.paginglibrarytry;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class GameDataRepository {
    private LiveData pagedGameList;
    private GameDataSourceFactory factory;

    public GameDataRepository(){
        factory = new GameDataSourceFactory();
        PagedList.Config config =
                new PagedList.Config.Builder()
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        //.setPrefetchDistance(2)
                        .setEnablePlaceholders(true).build();
                        //

        pagedGameList = new LivePagedListBuilder(factory, config)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build();
    }

    public LiveData<PagedList<Game>> getLivePagedGameList() {
        return pagedGameList;
    }
}
