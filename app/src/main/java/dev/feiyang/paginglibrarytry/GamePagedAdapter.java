package dev.feiyang.paginglibrarytry;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class GamePagedAdapter extends PagedListAdapter<Game, GamePagedAdapter.GameViewHolder> {
    public class GameViewHolder extends RecyclerView.ViewHolder{
        ImageView gameImage;
        TextView gameTitle;
        TextView gameGenre;
        TextView gameRating;
        TextView gameRCount;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gameImage = itemView.findViewById(R.id.game_image);
            this.gameTitle = itemView.findViewById(R.id.game_title);
            this.gameGenre = itemView.findViewById(R.id.game_genre);
            this.gameRating = itemView.findViewById(R.id.game_rating);
            this.gameRCount = itemView.findViewById(R.id.game_rcount);
        }
    }

    protected GamePagedAdapter() {
        super(new DiffUtil.ItemCallback<Game>() {
            @Override
            public boolean areItemsTheSame(@NonNull Game oldItem, @NonNull Game newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Game oldItem, @NonNull Game newItem) {
                return false;
            }
        });
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View emptyGameInfoView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_row_layout, parent, false);
        return new GameViewHolder(emptyGameInfoView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Log.d("Adapter", "Combining position: " + position);
        Game nGame = getItem(position);
        if (nGame == null){
            holder.gameGenre.setText("Loading....");
            return;
        }

        holder.gameTitle.setText(nGame.getTitle());
        holder.gameGenre.setText(nGame.getGenre() + ", " + nGame.getSubgenre());
        holder.gameRating.setText(Double.toString(nGame.getRating()));       // double --> string

        int rCountK = (int) nGame.getrCount() / 1000;// get rCount in thousands

        if (rCountK > 0){
            holder.gameRCount.setText(rCountK + "k ratings");
        } else {
            holder.gameRCount.setText("<1k ratings");
        }

        // load game icon into the row's icon view.
        Picasso.get().load(nGame.getImgURL()).into(holder.gameImage);
    }
}
