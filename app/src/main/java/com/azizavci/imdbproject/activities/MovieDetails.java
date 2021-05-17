package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.azizavci.imdbproject.R;

public class MovieDetails extends AppCompatActivity {

    private String title;
    private String genres;
    private String actors;
    private String director;
    private String producer;
    private int year;
    private int runtime;
    private Double rating;

    private TextView tv_title;
    private TextView tv_year;
    private TextView tv_genres;
    private TextView tv_producer;
    private TextView tv_director;
    private TextView tv_actors;
    private TextView tv_runtime;
    private RatingBar rb_ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initComponents();
        getIncomingIntent();

    }

    public void initComponents() {

        tv_title = findViewById(R.id.tv_title);
        tv_genres=findViewById(R.id.tv_genres);
        tv_year=findViewById(R.id.tv_year);
        tv_actors=findViewById(R.id.tv_actors);
        tv_director=findViewById(R.id.tv_director);
        tv_runtime=findViewById(R.id.tv_runtime);
        tv_producer=findViewById(R.id.tv_producer);
        rb_ratingBar = findViewById(R.id.rb_imdb);

    }

    private void getIncomingIntent() {

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        year =  intent.getIntExtra("year",0);
        runtime = intent.getIntExtra("runtime",0);
        actors =intent.getStringExtra("actors");
        producer = intent.getStringExtra("title");
        rating = intent.getDoubleExtra("rating", 0);

        tv_title.setText(getString(R.string.title)+" : "+title);
        tv_year.setText(getString(R.string.year)+" : "+String.valueOf(year));
        tv_runtime.setText(getString(R.string.runtime)+" : "+String.valueOf(runtime));
        tv_actors.setText(getString(R.string.actors)+" : " + actors);
        tv_producer.setText(getString(R.string.producer)+" : " + producer);
        tv_director.setText(getString(R.string.director)+" : " + director);

        rb_ratingBar.setRating(rating.floatValue());

    }
}