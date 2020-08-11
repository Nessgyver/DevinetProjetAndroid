package com.example.devinet.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.devinet.bo.Mot;
import com.example.devinet.repository.AppRepositoryFactory;
import com.example.devinet.repository.IMotRepository;

import java.util.List;

public class MotViewModel extends AndroidViewModel {

    private IMotRepository motRepository;
    private LiveData<List<Mot>> motsObs;
    private MutableLiveData<List<Mot>> motsSpeObs;

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

    public MutableLiveData<List<Mot>> get(int idCategorie){
        motsSpeObs.setValue(motRepository.get(idCategorie));
        return motsSpeObs;
    }

    public void update(Mot mot){
        motRepository.update(mot);
    }

    public void delete(Mot mot){
        motRepository.delete(mot);
    }

//    public LiveData<Integer> getProgressionCategorie(int idCategorie){
//        LiveData<Integer> progression = null;
//        int motOk = 0;
//
//        List<Mot> listeMots = motRepository.get(idCategorie);
//
//        for (int i = 0; i < listeMots.size(); i++) {
//            Mot motCourant = listeMots.get(i);
//            if(motCourant.getMot().equals(motCourant.getProposition())){
//                motOk++;
//            }
//        }
//
//        return progression.motOk / listeMots.size()*100;
//    }
}
