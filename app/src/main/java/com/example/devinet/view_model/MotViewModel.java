package com.example.devinet.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.devinet.bo.Mot;
import com.example.devinet.repository.AppRepositoryFactory;
import com.example.devinet.repository.IMotRepository;

import java.util.List;

public class MotViewModel extends AndroidViewModel {

    private IMotRepository motRepository;
    private LiveData<List<Mot>> motsObs;

    public MotViewModel(@NonNull Application application) {
        super(application);
        motRepository = AppRepositoryFactory.getMotRepository(application);
        motsObs = motRepository.get();
    }

    public void insert(Mot mot){
        motRepository.insert(mot);
    }

    public void insert(Mot ... mots){
        motRepository.insert(mots);
    }

    public LiveData<List<Mot>> get() {
        return motsObs;
    }

    public LiveData<List<Mot>> get(int idCategorie){
        return motRepository.get(idCategorie);
    }

    public void update(Mot mot){
        motRepository.update(mot);
    }

    public void delete(Mot mot){
        motRepository.delete(mot);
    }


}
