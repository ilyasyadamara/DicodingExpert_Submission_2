package dicoding.ilyasya.project.moviecatalogueuiux;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {

    private ArrayList<Movie> listMovie;
    public ListMovieAdapter(ArrayList<Movie> list){
        this.listMovie = list;
    }
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movies, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final Movie movie = listMovie.get(position);

        //Glide.with(holder.itemView.getContext())
        //        .load((movie.getPoster()))
        //        .apply(new RequestOptions().override(55, 55))
        //        .into(holder.imgPhoto);


        holder.imgPhoto.setImageResource(movie.getPoster());
        holder.txtJudul.setText(movie.getJudul());
        holder.txtRilis.setText(movie.getTanggal());
        holder.txtRating.setText(movie.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul;
        private TextView txtRilis;
        private TextView txtRating;
        //private TextView txtDeskripsi;
        private ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul);
            txtRilis = itemView.findViewById(R.id.txt_rilis);
            txtRating = itemView.findViewById(R.id.txt_rating);
            //txtDeskripsi = view.findViewById(R.id.txt_deskripsi);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }
}
