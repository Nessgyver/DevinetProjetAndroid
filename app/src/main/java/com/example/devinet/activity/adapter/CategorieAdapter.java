package com.example.devinet.activity.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.devinet.bo.Categorie;

import java.util.List;

public class CategorieAdapter extends ArrayAdapter<Categorie> {
    public CategorieAdapter(@NonNull Context context, int resource, @NonNull List<Categorie> objects) {
        super(context, resource, objects);
    }
}
