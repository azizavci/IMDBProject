package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.azizavci.imdbproject.R;

public class MovieDetails extends AppCompatActivity {

    private String title;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initComponents();
        getIncomingIntent();

    }

    public void initComponents(){

        tv_title=findViewById(R.id.tv_title);

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("title")) {

            title = getIntent().getStringExtra("title");
            tv_title.setText(title);
        }
    }
}