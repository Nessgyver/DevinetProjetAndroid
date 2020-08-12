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
        Toast.makeText(this, view.toString(), Toast.LENGTH_SHORT).show();
    }
}