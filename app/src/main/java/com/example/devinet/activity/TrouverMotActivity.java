package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.devinet.R;

public class TrouverMotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouver_mot);
    }

    public void onClickAjouterLettre(View view) {
        Toast.makeText(this, "onClickAjouterLettre", Toast.LENGTH_SHORT).show();   Toast.makeText(this, "onClickAjouterLettre", Toast.LENGTH_SHORT).show();
    }

    public void onClickPasser(View view) {
        Toast.makeText(this, "onClickPasser", Toast.LENGTH_SHORT).show();   Toast.makeText(this, "onClickAjouterLettre", Toast.LENGTH_SHORT).show();
    }

    public void onClickValider(View view) {
        Toast.makeText(this, "onClickValider", Toast.LENGTH_SHORT).show();   Toast.makeText(this, "onClickAjouterLettre", Toast.LENGTH_SHORT).show();
    }
}