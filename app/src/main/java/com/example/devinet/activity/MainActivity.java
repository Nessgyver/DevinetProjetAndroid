package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.devinet.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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