package com.azizavci.imdbproject.models;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_list")
public class FavList {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "film_Adi")
    private String filmAdi;


    @ColumnInfo(name = "fav_status")
    private String favStatus;
public FavList(long id, String filmAdi, String favStatus){
        this.id =id;
        this.filmAdi=filmAdi;
        this.favStatus=favStatus;
    }

    public long getId(){return id; }
    public void setId(long id){this.id=id;}
    public String getFilmAdi(){return filmAdi;}
    public void setFilmAdi(String filmAdi){this.filmAdi=filmAdi;}
    public String getFavStatus() {return favStatus;}
    public void setFavStatus(String favStatus) { this.favStatus = favStatus;}


}
