package com.example.devinet.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.devinet.bo.Categorie;

import java.util.List;

/**
 * Cette classe permet de donner les consignes nécessaires à Room
 * pour les interactions avec la base de données
 */
@Dao
public interface CategorieDAO {

    @Insert
    void insert(Categorie categorie);

    @Insert
    void insert(Categorie ... categories);

    @Query("SELECT * FROM Categorie")
    LiveData<List<Categorie>> get();

    @Query("SELECT * FROM Categorie WHERE id = :id")
    Categorie get(int id);

    @Update
    void update(Categorie categorie);

    @Delete
    void delete(Categorie categorie);
}
