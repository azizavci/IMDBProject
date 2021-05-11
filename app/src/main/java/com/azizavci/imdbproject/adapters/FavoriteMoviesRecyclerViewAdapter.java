package com.azizavci.imdbproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azizavci.imdbproject.R;
import com.azizavci.imdbproject.models.AppDatabase;
import com.azizavci.imdbproject.models.FavList;

import java.util.List;

public class FavoriteMoviesRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteMoviesRecyclerViewAdapter.ViewHolder> {
    private List<FavList> film;
    Context context;

    public FavoriteMoviesRecyclerViewAdapter(Context context, List<FavList> list){
        this.context=context;
        this.film=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView filmAdi;
        ImageButton favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filmAdi= itemView.findViewById(R.id.filmAdi);
            favBtn=itemView.findViewById(R.id.favBtn);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_movies_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);
        holder.filmAdi.setText(film.get(position).getFilmAdi());
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appDatabase.getFavDao().getFilm(holder.filmAdi.getText().toString())!= null) {
                    appDatabase.getFavDao().deleteFavList(appDatabase.getFavDao().getFilm(holder.filmAdi.getText().toString()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return film.size();
    }



}
