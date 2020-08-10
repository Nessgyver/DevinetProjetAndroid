package com.example.devinet.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.devinet.bo.Categorie;

import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO
 */
public interface ICategorieRepository {

    void insert(Categorie categorie);
    void insert(Categorie ... categories);
    void update(Categorie categorie);
    void delete(Categorie categorie);
    LiveData<List<Categorie>> get();
    MutableLiveData<Categorie> get(int idCategorie);
}
