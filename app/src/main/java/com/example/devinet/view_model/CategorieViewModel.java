package com.example.devinet.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.devinet.bo.Categorie;
import com.example.devinet.repository.AppRepositoryFactory;
import com.example.devinet.repository.ICategorieRepository;

import java.util.List;

public class CategorieViewModel extends AndroidViewModel {

    private ICategorieRepository categorieRepository;
    private LiveData<List<Categorie>> categoriesObs;

    public CategorieViewModel(@NonNull Application application) {
        super(application);
        categorieRepository = AppRepositoryFactory.getCategorieRepository(application);
        categoriesObs = categorieRepository.get();
    }


    public void insert(Categorie categorie){
        categorieRepository.insert();
    }

    public void insert(Categorie ... categories){
        categorieRepository.insert(categories);
    }

    public LiveData<List<Categorie>> get(){
        return categoriesObs;
    }

    public LiveData<Categorie> get(int idCategorie){
        return categorieRepository.get(idCategorie);
    }

    public void update(Categorie categorie){
        categorieRepository.update(categorie);
    }

    public void delete(Categorie categorie){
        categorieRepository.delete(categorie);
    }
}
