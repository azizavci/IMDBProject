package com.azizavci.imdbproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.activities.AllMovies;
import com.azizavci.imdbproject.models.Category;
import com.azizavci.imdbproject.models.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> implements Filterable {

    public Context context;
    private List<Category> categoryList;
    private List<Category> filteredCategoryList;
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    public String text;

    public CategoryRecyclerViewAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_row, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);

        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.CategoryViewHolder holder, int position) {

        holder.tv_categoryName.setText(categoryList.get(position).getCategoryName().toUpperCase());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String categoryName=holder.tv_categoryName.getText().toString();
                Intent intent=new Intent(context, AllMovies.class);
                intent.putExtra("categoryName",categoryName);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.categoryList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Category> filteredList = new ArrayList<>();


            for (Category category : filteredCategoryList) {
                if (category.getCategoryName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                    filteredList.add(category);
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {

            categoryList.clear();
            categoryList.addAll((Collection<? extends Category>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_categoryName;
        LinearLayout parentLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_categoryName = itemView.findViewById(R.id.tv_categoryName);
            parentLayout =itemView.findViewById(R.id.category_recycler_row);

        }
    }
}
