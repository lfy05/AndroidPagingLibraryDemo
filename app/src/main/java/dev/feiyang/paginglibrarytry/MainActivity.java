package dev.feiyang.paginglibrarytry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dev.feiyang.paginglibrarytry.databinding.ActivityMainBinding;

import static dev.feiyang.paginglibrarytry.R.id.nav_drawer;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainActivityVM mMainActivityVM;
    private ActivityMainBinding activityMainBinding;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        recyclerView = activityMainBinding.include.recyclerview;
        final GamePagedAdapter adapter = new GamePagedAdapter(new GamePagedAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(Game game) {
                Toast.makeText(getApplicationContext(), "You Clicked " + game.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        GameDataRepository repository = new GameDataRepository();
        mMainActivityVM = new ViewModelProvider(this).get(MainActivityVM.class);
        mMainActivityVM.getLivePagedList().observe(this, games -> {
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
        });
        //recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener());
        DrawerLayout drawer= activityMainBinding.navDrawer;

        NavController navController = Navigation.findNavController(this, R.id.navHost);
        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .setOpenableLayout(drawer).build();
        Toolbar toolbar = activityMainBinding.include.toolbar;
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // this displays burger menu icon
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // this hooks up the navigation
        NavigationUI.setupWithNavController(activityMainBinding.navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.navHost);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}