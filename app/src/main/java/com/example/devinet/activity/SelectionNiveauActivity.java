package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.devinet.R;
import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.CategorieViewModel;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class SelectionNiveauActivity extends AppCompatActivity {

    private LiveData<List<Categorie>> obsCategories;
    private CategorieViewModel cvm;
    private ListView listeNiveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);
        cvm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        listeNiveau = findViewById(R.id.lv_niveau);
    }

    @Override
    protected void onResume() {
        super.onResume();
        obsCategories = cvm.get();

        obsCategories.observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                for(Categorie categorie : categories){
                    Toast.makeText(SelectionNiveauActivity.this, categorie.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}