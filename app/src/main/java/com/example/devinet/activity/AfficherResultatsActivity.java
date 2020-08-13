package com.example.devinet.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.devinet.R;
import com.example.devinet.activity.adapter.ResultatAdapter;
import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.CategorieViewModel;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class AfficherResultatsActivity extends AppCompatActivity {

    private LiveData<List<Categorie>> obsCategories;
    private LiveData<List<Mot>> obsMots;
    private CategorieViewModel cvm;
    private MotViewModel mvm;
    private ListView listeNiveau;
    private ProgressBar progressionGenerale;
    TextView tvResultats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_resultats);
        cvm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        mvm = ViewModelProviders.of(this).get(MotViewModel.class);
        listeNiveau = findViewById(R.id.lv_resultats);
        progressionGenerale = findViewById(R.id.pb_resultats);
        tvResultats = findViewById(R.id.tv_resultats);
        obsCategories = cvm.get();
        obsMots = mvm.get();

    }

    @Override
    protected void onResume() {
        super.onResume();

        obsCategories.observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                ResultatAdapter adapter = new ResultatAdapter(AfficherResultatsActivity.this, R.layout.style_ligne_niveau, categories);
                listeNiveau.setAdapter(adapter);
            }
        });

        obsMots.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                float motOk = 0;
                if (mots.size()>0){
                    for(Mot mot : mots){
                        if (mot.getMot().equals(mot.getProposition())){
                            motOk ++;
                        }
                    }
                    int resultat = (int)(motOk/mots.size()*100);
                    progressionGenerale.setProgress(resultat);
                    String resultatString = String.valueOf(tvResultats.getText()) + resultat + " %";
                    tvResultats.setText(resultatString);
                }
            }
        });


    }
}