package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.adapters.MovieRecyclerViewAdapter;
import com.azizavci.imdbproject.adapters.RecommendedMovieRecyclerAdapter;
import com.azizavci.imdbproject.functions.Functions;
import com.azizavci.imdbproject.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button favListBtn;
    private Button categoriesBtn;
    public DrawerLayout drawerLayout;
    private RecyclerView rv_recommendedMovies;
    private final String JSON_URL = "https://raw.githubusercontent.com/retaildevcrews/imdb/main/data/movies.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Movie> movies;
    public RecommendedMovieRecyclerAdapter myadapter;
    Functions functions = new Functions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        registerEventHandlers();
        jsonRequest();

        RecyclerView recyclerView = findViewById(R.id.rv_recommendedMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter = new RecommendedMovieRecyclerAdapter(MainActivity.this, movies);
        recyclerView.setAdapter(myadapter);
    }


    private void registerEventHandlers() {
        favListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteMoviesActivity.class);
                startActivity(intent);

            }
        });

        categoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllCategories.class);
                startActivity(intent);
            }
        });


    }

    private void initComponents() {

        drawerLayout = findViewById(R.id.drawer_menu);
        favListBtn = findViewById(R.id.favListBtn);
        categoriesBtn = findViewById(R.id.button2);
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

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

    private void setuprecyclerview(List<Movie> movies) {

        myadapter = new RecommendedMovieRecyclerAdapter(this, movies);
        rv_recommendedMovies.setLayoutManager(new LinearLayoutManager(this));
        rv_recommendedMovies.setAdapter(myadapter);

    }

    public void clickBack(View view) {
        finish();
    }

    public void clickMenu(View view) {
        functions.openDrawer(drawerLayout);
    }

    public void toAllMoviesPage(View view) {
        Intent intent = new Intent(MainActivity.this, AllMovies.class);
        startActivity(intent);

    }

    public void toHome(View view) {

        functions.closeDrawer(drawerLayout);

    }

    public void toCategories(View view) {

        Intent intent = new Intent(MainActivity.this, AllCategories.class);
        startActivity(intent);

    }

    public void clickAllMovies(View view) {

        functions.redirectActivity(MainActivity.this, AllMovies.class);

    }

    public void clickLogout(View view) {

        functions.LogOut(MainActivity.this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //functions.closeDrawer(drawerLayout);
    }
}