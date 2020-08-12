package com.example.devinet.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.devinet.bo.Mot;

import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO
 */
public interface IMotRepository {
    void insert(Mot mot);
    void insert(Mot ... mot);
    void update(Mot mot);
    void update(int idCategorie);
    void delete(Mot mot);
    LiveData<List<Mot>> get();
    LiveData<List<Mot>> get(int idCategorie);
}
