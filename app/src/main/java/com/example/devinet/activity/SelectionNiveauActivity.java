package com.example.devinet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class SelectionNiveauActivity extends AppCompatActivity {

    private LiveData<List<Categorie>> obsCategories;
    private CategorieViewModel cvm;
    private ListView listeNiveau;
    private MotViewModel mvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);
        cvm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        mvm = ViewModelProviders.of(this).get(MotViewModel.class);
        listeNiveau = findViewById(R.id.lv_niveau);

        listeNiveau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categorie categorie = (Categorie) listeNiveau.getItemAtPosition(i);
                Intent intent  = new Intent(SelectionNiveauActivity.this, SelectionListeActivity.class);
                intent.putExtra("idCategorie", categorie.getId());
                startActivity(intent);
            }
        });

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

    public void onClickReinitialiser(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Categorie categorie = (Categorie) listeNiveau.getItemAtPosition(position);

        mvm.update(categorie.getId());
        Toast.makeText(this, "catégorie : " + categorie.getNom() + "Réinitialisée", Toast.LENGTH_LONG).show();
    }
}
