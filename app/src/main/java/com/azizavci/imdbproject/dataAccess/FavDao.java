package com.azizavci.imdbproject.dataAccess;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.azizavci.imdbproject.models.FavList;

import java.util.List;

@Dao
public interface FavDao {
    @Insert
    void insertFavList(FavList favlist);
    @Update
    void updateFavList(FavList favList);
    @Delete
    void deleteFavList(FavList favList);
    @Query("SELECT * FROM fav_list")
    List<FavList> loadAllFavList();
    @Query("SELECT * FROM fav_list WHERE film_Adi=:filmAdi")
    FavList getFilm(String filmAdi);
    @Query("SELECT film_Adi FROM fav_list GROUP BY film_Adi")
    List<String> getAllFilmAdi();
}