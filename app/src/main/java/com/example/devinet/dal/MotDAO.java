package com.example.devinet.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;

import java.util.List;

/**
 * Cette classe permet de donner les consignes nécessaires à Room
 * pour les interactions avec la base de données
 */
@Dao
public interface MotDAO {
    @Insert
    void insert(Mot mot);

    @Insert
    void insert(Mot ... mots);

    @Update
    void update(Mot mot);

    @Query("UPDATE Mot SET proposition = null WHERE idCategorie = :idCategorie")
    void update(int idCategorie);

    @Delete
    void delete(Mot mot);

    @Query("SELECT * FROM Mot")
    LiveData<List<Mot>> get();

    @Query("SELECT * FROM Mot WHERE idCategorie = :idCategorie")
    LiveData<List<Mot>> get(int idCategorie);
}
