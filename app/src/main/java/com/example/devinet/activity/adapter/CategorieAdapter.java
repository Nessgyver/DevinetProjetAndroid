package com.example.devinet.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.devinet.R;
import com.example.devinet.activity.SelectionNiveauActivity;
import com.example.devinet.bo.Categorie;
import com.example.devinet.view_model.MotViewModel;

import java.util.List;

public class CategorieAdapter extends ArrayAdapter<Categorie> {

    private Context context;

    public CategorieAdapter(@NonNull Context context, int resource, @NonNull List<Categorie> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View nouvelleLigne, @NonNull ViewGroup parent) {

        MotViewModel mvm = ViewModelProviders.of((FragmentActivity) context).get(MotViewModel.class);
        Categorie categorie = getItem(position);

        if(nouvelleLigne == null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nouvelleLigne = li.inflate(R.layout.style_ligne_niveau, parent, false);
        }

        TextView tvNiveau = nouvelleLigne.findViewById(R.id.tv_ligne_niveau);
        ProgressBar pbNiveau = nouvelleLigne.findViewById(R.id.pb_ligne_niveau);

        tvNiveau.setText(categorie.getNom());
//        pbNiveau.setProgress(mvm.getProgressionCategorie(categorie.getId()));

        return  nouvelleLigne;
    }
}
