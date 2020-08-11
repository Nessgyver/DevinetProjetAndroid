package com.example.devinet.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.devinet.R;
import com.example.devinet.activity.adapter.CategorieAdapter;
import com.example.devinet.bo.Categorie;
import com.example.devinet.view_model.CategorieViewModel;

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
                CategorieAdapter adapter = new CategorieAdapter(SelectionNiveauActivity.this, R.layout.style_ligne_niveau, categories);
                listeNiveau.setAdapter(adapter);
            }
        });

    }
}