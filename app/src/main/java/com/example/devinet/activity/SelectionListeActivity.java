package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_preferences:
                Intent intentPreferences = new Intent(this, MesPreferencesActivity.class);
                startActivity(intentPreferences);
                return true;
            case R.id.action_a_propos:
                Intent intentAPropos = new Intent(this, AProposActivity.class);
                startActivity(intentAPropos);
                return true;
            case R.id.action_retour:
                Intent intentRetour = new Intent(this, MainActivity.class);
                startActivity(intentRetour);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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