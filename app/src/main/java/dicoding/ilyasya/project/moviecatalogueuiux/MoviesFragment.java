package dicoding.ilyasya.project.moviecatalogueuiux;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        list.addAll(getListMovies());
        showRecyclerList();
        return view;
    }

    private ArrayList<Movie> getListMovies() {
        String[] dataJudul = getResources().getStringArray(R.array.data_judul);
        String[] dataRilis = getResources().getStringArray(R.array.data_rilis);
        String[] dataRating = getResources().getStringArray(R.array.data_rating);
        String[] dataStatus = getResources().getStringArray(R.array.data_status);
        String[] dataBahasa = getResources().getStringArray(R.array.data_bahasa);
        String[] dataDurasi = getResources().getStringArray(R.array.data_durasi);
        String[] dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi);
        @SuppressLint("Recycle") TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_poster);
        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataJudul.length; i++) {
            Movie movie = new Movie();
            movie.setJudul(dataJudul[i]);
            movie.setTanggal(dataRilis[i]);
            movie.setRating(dataRating[i]);
            movie.setDeskripsi(dataDeskripsi[i]);
            movie.setStatus(dataStatus[i]);
            movie.setBahasa(dataBahasa[i]);
            movie.setDurasi(dataDurasi[i]);
            movie.setPoster(dataPhoto.getResourceId(i, -1));
            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                    showSelectedHero(data);
            }
        });
    }

    private void showSelectedHero(Movie movie) {
        Intent detailMovie = new Intent(getContext(), DetailActivity.class);
        detailMovie.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(detailMovie);
    }
}
