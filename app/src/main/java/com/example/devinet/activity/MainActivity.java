package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.devinet.R;
import com.facebook.stetho.Stetho;

/**
 * Contrôleur de l'écran d'accueil de l'application
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        menu.findItem(R.id.action_retour).setVisible(false);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickJouer(View view) {
        Intent intent = new Intent(this, SelectionNiveauActivity.class);
        startActivity(intent);
    }

    public void onClickResults(View view) {
        Intent intent = new Intent(this, AfficherResultatsActivity.class);
        startActivity(intent);
    }

    public void onClickProposer(View view) {
        Intent intent = new Intent(this, NouvellePropositionActivity.class);
        startActivity(intent);
    }

    public void onClickQuitter(View view) {
        Intent intent = new Intent(this, QuitterApplicationActivity.class);
        startActivity(intent);
    }
}