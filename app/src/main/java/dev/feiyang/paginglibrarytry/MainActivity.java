package dev.feiyang.paginglibrarytry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainActivityVM mMainActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        final GamePagedAdapter adapter = new GamePagedAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        GameDataRepository repository = new GameDataRepository();
        mMainActivityVM = new ViewModelProvider(this).get(MainActivityVM.class);
        mMainActivityVM.getLivePagedList().observe(this, new Observer<PagedList<Game>>() {
            @Override
            public void onChanged(PagedList<Game> games) {
                Log.d("Dataset", "Changed: size - " + games.size());
                adapter.submitList(games);
                adapter.notifyDataSetChanged();
                games.addWeakCallback(null, new PagedList.Callback() {
                    @Override
                    public void onChanged(int position, int count) {
                        Log.d("list changed", "size" + count);
                    }

                    @Override
                    public void onInserted(int position, int count) {
                        Log.d("list inserted", "size" + count);
                    }

                    @Override
                    public void onRemoved(int position, int count) {

                    }
                });
            }
        });
    }
}