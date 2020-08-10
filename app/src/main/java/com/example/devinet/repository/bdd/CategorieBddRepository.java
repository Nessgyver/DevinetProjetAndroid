package com.example.devinet.repository.bdd;


import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.devinet.bo.Categorie;
import com.example.devinet.dal.AppDatabase;
import com.example.devinet.dal.CategorieDAO;
import com.example.devinet.repository.ICategorieRepository;

import java.util.List;

public class CategorieBddRepository implements ICategorieRepository {

    private CategorieDAO daoCategorie;
    private MutableLiveData<Categorie> categorie;

    public CategorieBddRepository(Context context) {
        // instance de ma BDD
        AppDatabase devinetBdd = AppDatabase.getInstance(context);

        // instance de la DAO Categorie
        daoCategorie = devinetBdd.getCategorieDAO();
    }

    @Override
    public void insert(Categorie categorie) {
        new AsyncTask<Categorie, Void, Void>(){
            @Override
            protected Void doInBackground(Categorie... categories) {
                daoCategorie.insert(categories);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public void insert(Categorie... categories) {
        new AsyncTask<Categorie, Void, Void>(){
            @Override
            protected Void doInBackground(Categorie... categories) {
                daoCategorie.insert(categories);
                return null;
            }
        }.execute(categories);
    }

    @Override
    public void update(Categorie categorie) {
        new AsyncTask<Categorie, Void, Void>(){
            @Override
            protected Void doInBackground(Categorie... categories) {
                daoCategorie.update(categories[0]);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public void delete(Categorie categorie) {
        new AsyncTask<Categorie, Void, Void>(){
            @Override
            protected Void doInBackground(Categorie... categories) {
                daoCategorie.delete(categories[0]);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public LiveData<List<Categorie>> get() {
        return daoCategorie.get();
    }

    @Override
    public MutableLiveData<Categorie> get(int idCategorie) {
        categorie.setValue(daoCategorie.get(idCategorie));
        return categorie;
    }
}
