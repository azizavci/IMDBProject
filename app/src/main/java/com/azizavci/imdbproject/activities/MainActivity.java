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
    DrawerLayout drawerLayout;
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


    }



    private void initComponents() {
        drawerLayout = findViewById(R.id.drawer_layout_home);
        favListBtn=findViewById(R.id.favListBtn);
    }

    public void clickMenu(View view) {
        functions.openDrawer(drawerLayout);
    }

    public void toAllMoviesPage(View view){
        Intent intent = new Intent(MainActivity.this, AllMovies.class);
        startActivity(intent);

    }

    public void clickLogo(View view) {
        functions.closeDrawer(drawerLayout);
    }

    public void clickHome(View view) {
        functions.closeDrawer(drawerLayout);
    }

    public void clickDashBoard(View view) {
        //redirect activity to dashboard
        //functions.redirectActivity(this, Dashboard.class);
    }

    public void clickAboutUs(View view) {
        //redirect activity to about us
        //functions.redirectActivity(this, AboutUs.class);
    }

    public void clickLogin(View view) {

        //functions.redirectActivity(this, Account.class);
    }

    public void clickLogout(View view) {
        //close app
        //functions.LogOut(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //functions.closeDrawer(drawerLayout);
    }
}