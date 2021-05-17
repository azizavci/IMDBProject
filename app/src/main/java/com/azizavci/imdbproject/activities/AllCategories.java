package com.azizavci.imdbproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.adapters.CategoryRecyclerViewAdapter;
import com.azizavci.imdbproject.adapters.MovieRecyclerViewAdapter;
import com.azizavci.imdbproject.models.Category;
import com.azizavci.imdbproject.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class AllCategories extends AppCompatActivity {

    List<Category> categoryList=new ArrayList<>();
    private CategoryRecyclerViewAdapter myadapter;
    private RecyclerView recyclerView;
    private TextView tv_backToolbarTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        initComponents();
        addCategory();
        setuprecyclerview(categoryList);

        tv_backToolbarTextView.setText(R.string.allCategoriesPage);


    }
    private void setuprecyclerview(List<Category> categories) {

        tv_backToolbarTextView=findViewById(R.id.tv_backToolbarTextView);
        myadapter = new CategoryRecyclerViewAdapter(AllCategories.this,categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    private void initComponents() {

        recyclerView = findViewById(R.id.rv_categories);
    }


    private void addCategory(){

        categoryList.add(new Category(1,"action"));
        categoryList.add(new Category(2,"adventure"));
        categoryList.add(new Category(3,"animation"));
        categoryList.add(new Category(4,"biography"));
        categoryList.add(new Category(5,"comedy"));
        categoryList.add(new Category(6,"crime"));
        categoryList.add(new Category(7,"documentary"));
        categoryList.add(new Category(8,"drama"));
        categoryList.add(new Category(9,"family"));
        categoryList.add(new Category(10,"fantasy"));
        categoryList.add(new Category(11,"history"));
        categoryList.add(new Category(12,"horror"));
        categoryList.add(new Category(13,"music"));
        categoryList.add(new Category(14,"musical"));
        categoryList.add(new Category(15,"mystery"));
        categoryList.add(new Category(16,"romance"));
        categoryList.add(new Category(17,"sci-fi"));
        categoryList.add(new Category(18,"sport"));
        categoryList.add(new Category(19,"thriller"));
        categoryList.add(new Category(20,"war"));
    }

    public void clickBack(View view){
        finish();
    }
}