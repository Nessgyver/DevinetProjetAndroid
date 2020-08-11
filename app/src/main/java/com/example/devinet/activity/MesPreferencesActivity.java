package com.example.devinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.devinet.R;

public class MesPreferencesActivity extends AppCompatActivity {

    public static final String CLE_SON = "son";
    public static final String CLE_VIBRATION  = "vibration";
    public static final String NOM_FICHIER  = "parametres";

    private Switch sb_son = null;
    private Switch sb_vibration = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_preferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sb_son = findViewById(R.id.sb_son);
        sb_vibration = findViewById(R.id.sb_vibration);

        SharedPreferences sp = getSharedPreferences(NOM_FICHIER, MODE_PRIVATE);
        sb_son.setChecked(sp.getBoolean(CLE_SON, true));
        sb_vibration.setChecked(sp.getBoolean(CLE_VIBRATION, true));
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

        SharedPreferences sp = getSharedPreferences(NOM_FICHIER, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(CLE_SON, sb_son.isChecked());
        editor.putBoolean(CLE_VIBRATION, sb_vibration.isChecked());
        editor.commit();
    }
}
