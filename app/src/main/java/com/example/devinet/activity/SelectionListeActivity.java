package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.devinet.R;
import com.example.devinet.activity.adapter.CategorieAdapter;
import com.example.devinet.activity.adapter.ListeMotAdapter;
import com.example.devinet.bo.Categorie;
import com.example.devinet.view_model.CategorieViewModel;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class SelectionListeActivity extends AppCompatActivity {

    private CategorieViewModel cvm;
    private MotViewModel mvm;

    private LiveData<List<Categorie>> observerCategories;
    private ListView listeMot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_liste);

        cvm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        mvm = ViewModelProviders.of(this).get(MotViewModel.class);

        listeMot = findViewById(R.id.lv_liste);

        listeMot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categorie categorie = (Categorie) listeMot.getItemAtPosition(i);
                Intent intent  = new Intent(SelectionListeActivity.this, TrouverMotActivity.class);
                intent.putExtra("idCategorie", categorie.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        observerCategories = cvm.get();

        Intent intent = getIntent();
        final int choix = intent.getIntExtra("idCategorie", 1);

        observerCategories.observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                ListeMotAdapter adapter = new ListeMotAdapter(SelectionListeActivity.this, R.layout.style_ligne_niveau, categories);
                listeMot.setAdapter(adapter);
            }
        });
    }
}