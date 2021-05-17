package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.functions.Functions;


public class MainActivity extends AppCompatActivity {



    private Button favListBtn;
    private Button categoriesBtn;
    public DrawerLayout drawerLayout;
    Functions functions = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        registerEventHandlers();
    }


    private void registerEventHandlers() {
        favListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, FavoriteMoviesActivity.class);
                startActivity(intent);

            }
        });

        categoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, AllCategories.class);
                startActivity(intent);
            }
        });


    }

    private void initComponents() {
        drawerLayout = findViewById(R.id.drawer_menu);
        favListBtn=findViewById(R.id.favListBtn);
        categoriesBtn=findViewById(R.id.button2);
    }

    public void clickMenu(View view) {
        functions.openDrawer(drawerLayout);
    }

    public void toAllMoviesPage(View view){
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