package com.azizavci.imdbproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.adapters.FavoriteMoviesRecyclerViewAdapter;
import com.azizavci.imdbproject.adapters.MovieRecyclerViewAdapter;
import com.azizavci.imdbproject.models.AppDatabase;
import com.azizavci.imdbproject.models.FavList;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesActivity extends AppCompatActivity {

    public List<FavList> film;
    private RecyclerView recyclerView;
    private ImageButton home,paylas;
    public List<String> favorilerListesi;
    private TextView tv_backToolbarTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_movies);

        film = new ArrayList<>();
        favorilerListesi = new ArrayList<>();

        initComponents();
        registerEventHandlers();
        databaseRequest();
        setuprecyclerview();

        tv_backToolbarTextView.setText(R.string.favoritesPage);


    }

    private void initComponents() {
        recyclerView=findViewById(R.id.fav_recyclerview);
        recyclerView.setHasFixedSize(true);
        paylas=findViewById(R.id.share);
        home = findViewById(R.id.home);
        tv_backToolbarTextView=findViewById(R.id.tv_backToolbarTextView);

    }


    private void registerEventHandlers() {
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

    private void databaseRequest() {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        film = appDatabase.getFavDao().loadAllFavList();
        favorilerListesi = appDatabase.getFavDao().getAllFilmAdi();

    }


    private void setuprecyclerview(){
        FavoriteMoviesRecyclerViewAdapter favAdapter = new FavoriteMoviesRecyclerViewAdapter(this,film);

        recyclerView.setAdapter(favAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void clickBack(View view){
        finish();
    }

}