package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.devinet.R;

public class MesPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_preferences);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        menu.findItem(R.id.action_preferences).setVisible(false);
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
    protected void onPause() {
        super.onPause();
    }
}