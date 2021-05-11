package com.azizavci.imdbproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.adapters.FavoriteMoviesRecyclerViewAdapter;
import com.azizavci.imdbproject.adapters.MovieRecyclerViewAdapter;
import com.azizavci.imdbproject.models.AppDatabase;
import com.azizavci.imdbproject.models.FavList;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesActivity extends AppCompatActivity {
    public List<FavList> film;
    private RecyclerView recyclerView;
    private ImageButton paylas;
    public List<String> favorilerListesi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
       film = new ArrayList<>();
       favorilerListesi = new ArrayList<>();

        super.onCreate(savedInstanceState);
        initComponents();
        setContentView(R.layout.favorite_movies);
        recyclerView=findViewById(R.id.fav_recyclerview);
        paylas=findViewById(R.id.share);
        registerEventHandlers();

        databaseRequest();

        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        film = appDatabase.getFavDao().loadAllFavList();
        favorilerListesi = appDatabase.getFavDao().getAllFilmAdi();

        FavoriteMoviesRecyclerViewAdapter favAdapter = new FavoriteMoviesRecyclerViewAdapter(this,film);

        recyclerView.setAdapter(favAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        paylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                int listSize = favorilerListesi.size();
                String mesaj=" ";


                for(int k=0; k<listSize;k++){
                    mesaj+=favorilerListesi.get(k)+" , ";
                    intent.putExtra(Intent.EXTRA_TEXT,mesaj);
                    intent.setType("text/plain");

                }

                startActivity(Intent.createChooser(intent, "Uygulama Seçiniz:"));
            }
        });

    }

    private void registerEventHandlers() {

    }

    private void databaseRequest() {

    }

    private void initComponents() {

    }

    private void setuprecyclerview(List<FavList> film){

    }

}
