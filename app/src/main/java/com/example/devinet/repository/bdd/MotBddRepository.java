package com.example.devinet.repository.bdd;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.devinet.bo.Mot;
import com.example.devinet.dal.AppDatabase;
import com.example.devinet.dal.MotDAO;
import com.example.devinet.repository.IMotRepository;

import java.util.List;

public class MotBddRepository implements IMotRepository {

    private MotDAO daoMot;

    public MotBddRepository(Context context) {
        //Instance de ma Bdd
        AppDatabase maBdd = AppDatabase.getInstance(context);

        //Instance de la DAO Article
        daoMot = maBdd.getMotDAO();
    }

    @Override
    public void insert(Mot mot) {
        new AsyncTask<Mot, Void, Void>(){
            @Override
            protected Void doInBackground(Mot... mots) {
                daoMot.insert(mots);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public void insert(Mot... mot) {
        new AsyncTask<Mot, Void, Void>(){
            @Override
            protected Void doInBackground(Mot... mots) {
                daoMot.insert(mots);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public void update(Mot mot) {
        new AsyncTask<Mot, Void, Void>(){
            @Override
            protected Void doInBackground(Mot... mots) {
                daoMot.update(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public void delete(Mot mot) {
        new AsyncTask<Mot, Void, Void>(){
            @Override
            protected Void doInBackground(Mot... mots) {
                daoMot.delete(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public LiveData<List<Mot>> get() {
        return daoMot.get();
    }

    @Override
    public List<Mot> get(int idCategorie) {
        return daoMot.get(idCategorie);
    }
}
