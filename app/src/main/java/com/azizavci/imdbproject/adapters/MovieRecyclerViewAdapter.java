package com.azizavci.imdbproject.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.models.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> data;

    RequestOptions option;

    public MovieRecyclerViewAdapter(Context context, List<Movie> data) {
        this.context = context;
        this.data = data;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.movie_recyclerview_row, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {




        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_rating.setText(String.valueOf(data.get(position).getRating()));
        holder.tv_category.setText(data.get(position).getGenres());

        Glide.with(context).load(data.get(position).getImageUrl()).apply(option).into(holder.iv_movieImage);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_movieImage;
        TextView tv_title;
        TextView tv_category;
        TextView tv_rating;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            iv_movieImage = itemView.findViewById(R.id.iv_movie_image);


        }
    }

}
