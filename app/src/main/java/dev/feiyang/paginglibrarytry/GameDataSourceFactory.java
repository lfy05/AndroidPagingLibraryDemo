package dev.feiyang.paginglibrarytry;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class GameDataSourceFactory extends DataSource.Factory {
    @NonNull
    @Override
    public DataSource create() {
        return new GameDataSource();
    }
}
