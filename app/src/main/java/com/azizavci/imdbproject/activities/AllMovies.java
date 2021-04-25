package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);

        movies = new ArrayList<>();

        initComponents();
        jsonRequest();
    }

    private void initComponents() {

        recyclerView = findViewById(R.id.rv_movies);
    }

    private void jsonRequest() {
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


        MovieRecyclerViewAdapter myadapter = new MovieRecyclerViewAdapter(this, movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }


}