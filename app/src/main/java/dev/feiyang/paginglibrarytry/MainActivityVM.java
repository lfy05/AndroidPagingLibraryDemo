package dev.feiyang.paginglibrarytry;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class MainActivityVM extends ViewModel {
    private GameDataRepository mGameDataRepository;

    public MainActivityVM() {
        super();
        mGameDataRepository = new GameDataRepository();
    }

    public LiveData<PagedList<Game>> getLivePagedList(){
        return mGameDataRepository.getLivePagedGameList();
    }
}
