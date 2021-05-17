package com.azizavci.imdbproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.activities.MovieDetails;
import com.azizavci.imdbproject.models.AppDatabase;
import com.azizavci.imdbproject.models.FavList;
import com.azizavci.imdbproject.models.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> implements Filterable {

    public static Context context;
    private List<Movie> data;
    private List<Movie> filteredData;

    RequestOptions option;

    public MovieRecyclerViewAdapter(Context context, List<Movie> data) {
        this.context = context;
        this.data = data;
        this.filteredData = new ArrayList<>(data);
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recyclerview_row, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);

        Movie movie = data.get(position);

        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_rating.setText(String.valueOf(data.get(position).getRating()));
        holder.tv_category.setText(data.get(position).getGenres());
        Glide.with(context).load(data.get(position).getImageUrl()).apply(option).into(holder.iv_movieImage);

        holder.position = position;
        holder.movie = movie;


        if (appDatabase.getFavDao().getFilm(movie.getTitle()) != null) {
            holder.btn_favorite.setBackgroundResource(R.drawable.favorite);
        } else {
            holder.btn_favorite.setBackgroundResource(R.drawable.fav_add);
        }


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MovieDetails.class);
                intent.putExtra("title",data.get(position).getTitle());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Movie> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(filteredData);

            } else {
                for (Movie movie : filteredData) {
                    if (movie.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            movie.getGenres().toLowerCase().contains("|" + charSequence.toString().toLowerCase() + "|")) {
                        filteredList.add(movie);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data.clear();
            data.addAll((Collection<? extends Movie>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public static class MovieViewHolder extends RecyclerView.ViewHolder {


        Movie movie;
        ImageView iv_movieImage;
        TextView tv_title;
        TextView tv_category;
        TextView tv_rating;
        ImageButton btn_favorite;
        LinearLayout parentLayout;
        int position;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            AppDatabase appDatabase = AppDatabase.getAppDatabase(context);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            iv_movieImage = itemView.findViewById(R.id.iv_movie_image);
            btn_favorite = itemView.findViewById(R.id.favButon);
            parentLayout = itemView.findViewById(R.id.rr_movie);


            itemView.setOnClickListener((v -> {
                Log.d("demo", "onClick: item clicked" + position + "movie" + movie.getTitle());
            }));

            // Favorilere Ekleme ve Silme
            btn_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("demo","onClick: Favori Film"+ movie.getTitle());


                    if (appDatabase.getFavDao().getFilm(movie.getTitle()) == null) {

                        FavList fav = new FavList(movie.getId(), movie.getTitle(), "1");
                        appDatabase.getFavDao().insertFavList(fav);
                        btn_favorite.setBackgroundResource(R.drawable.favorite);
                    } else {
                        if (appDatabase.getFavDao().getFilm(movie.getTitle()) != null) {
                            appDatabase.getFavDao().deleteFavList(appDatabase.getFavDao().getFilm(movie.getTitle()));
                            btn_favorite.setBackgroundResource(R.drawable.fav_add);
                        }
                    }
                }
            });

        }

    }
}