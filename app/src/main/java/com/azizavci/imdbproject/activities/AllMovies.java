package com.azizavci.imdbproject.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.adapters.MovieRecyclerViewAdapter;
import com.azizavci.imdbproject.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllMovies extends AppCompatActivity {

    private final String JSON_URL = "https://raw.githubusercontent.com/retaildevcrews/imdb/main/data/movies.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Movie> movies;
    private RecyclerView recyclerView;
    public MovieRecyclerViewAdapter myadapter;
    private SearchView searchView;
    private TextView tv_backToolbarTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);

        movies = new ArrayList<>();

        initComponents();
        jsonRequest();

        tv_backToolbarTextView.setText(R.string.allMoviesPage);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myadapter.getFilter().filter(newText);
                return true;
            }
        });

    }

    private void initComponents() {

        tv_backToolbarTextView = findViewById(R.id.tv_backToolbarTextView);
        recyclerView = findViewById(R.id.rv_movies);
        searchView = findViewById(R.id.searchview_movie);
    }


    public void jsonRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                JSONObject jsonObject = null;
                JSONArray jsonArray = null;


                for (int i = 0; i < response.length(); i++) {

                    try {

                        jsonObject = response.getJSONObject(i);
                        Movie movie = new Movie();

                        movie.setTitle(jsonObject.getString("title"));
                        movie.setRating(jsonObject.getDouble("rating"));
                        movie.setGenres(jsonObject.getString("genreSearch"));
                        movie.setYear(jsonObject.getInt("year"));
                        movie.setRuntime(jsonObject.getInt("runtime"));
                        movie.setActor(jsonObject.getJSONArray("roles").getJSONObject(0).getString("name"));
                        movie.setDirector(jsonObject.getJSONArray("roles").getJSONObject(1).getString("name"));
                        movie.setProducer(jsonObject.getJSONArray("roles").getJSONObject(2).getString("name"));

                        movies.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(movies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(AllMovies.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Movie> movies) {

        myadapter = new MovieRecyclerViewAdapter(this, movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myadapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void clickBack(View view) {
        finish();
    }

}