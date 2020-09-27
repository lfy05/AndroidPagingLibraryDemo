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

    public interface OnItemClickedListener{
        public void onItemClicked(Game game);
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // The listener for when this ViewHolder View is clicked.
        OnItemClickedListener onItemClickedListener;

        Game game;
        ImageView gameImage;
        TextView gameTitle;
        TextView gameGenre;
        TextView gameRating;
        TextView gameRCount;

        public GameViewHolder(@NonNull View itemView, OnItemClickedListener onItemClickedListener) {
            super(itemView);
            this.gameImage = itemView.findViewById(R.id.game_image);
            this.gameTitle = itemView.findViewById(R.id.game_title);
            this.gameGenre = itemView.findViewById(R.id.game_genre);
            this.gameRating = itemView.findViewById(R.id.game_rating);
            this.gameRCount = itemView.findViewById(R.id.game_rcount);
            // attach and setup callback
            this.onItemClickedListener = onItemClickedListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // invoke callback
            onItemClickedListener.onItemClicked(this.game);
        }

        public void setGame(Game game) {
            this.game = game;
            if (game == null){
                this.gameGenre.setText("Loading....");
                return;
            }

            this.gameTitle.setText(game.getTitle());
            this.gameGenre.setText(game.getGenre() + ", " + game.getSubgenre());
            this.gameRating.setText(Double.toString(game.getRating()));       // double --> string

            int rCountK = (int) game.getrCount() / 1000;// get rCount in thousands

            if (rCountK > 0){
                this.gameRCount.setText(rCountK + "k ratings");
            } else {
                this.gameRCount.setText("<1k ratings");
            }

            // load game icon into the row's icon view.
            Picasso.get().load(game.getImgURL()).into(this.gameImage);
        }
    }

    private OnItemClickedListener onItemClickedListener;

    protected GamePagedAdapter(OnItemClickedListener onItemClickedListener) {
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
        this.onItemClickedListener = onItemClickedListener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View emptyGameInfoView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_row_layout, parent, false);
        return new GameViewHolder(emptyGameInfoView, onItemClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Log.d("Adapter", "Combining position: " + position);
        Game nGame = getItem(position);
        holder.setGame(nGame);
    }
}
