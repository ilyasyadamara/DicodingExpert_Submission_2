package dicoding.ilyasya.project.moviecatalogueuiux;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        list.addAll(getListMovies());
        showRecyclerList();
        return view;
    }

    private ArrayList<Movie> getListMovies() {
        String[] dataJudul = getResources().getStringArray(R.array.data_judul2);
        String[] dataRilis = getResources().getStringArray(R.array.data_rilis2);
        String[] dataRating = getResources().getStringArray(R.array.data_rating2);
        String[] dataStatus = getResources().getStringArray(R.array.data_status2);
        String[] dataBahasa = getResources().getStringArray(R.array.data_bahasa2);
        String[] dataDurasi = getResources().getStringArray(R.array.data_durasi2);
        String[] dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi2);
        @SuppressLint("Recycle") TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_poster2);
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
