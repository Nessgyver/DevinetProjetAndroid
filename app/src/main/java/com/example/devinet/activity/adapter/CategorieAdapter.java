package com.example.devinet.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.devinet.R;
import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;
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
        final ProgressBar pbNiveau = nouvelleLigne.findViewById(R.id.pb_ligne_niveau);
        LiveData<List<Mot>> listeMotsCategorie = mvm.get(categorie.getId());

        listeMotsCategorie.observe((LifecycleOwner) this.getContext(), new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                float motOk = 0;
                if (mots.size()>0){
                    for(Mot mot : mots){
                        if (mot.getMot().equals(mot.getProposition())){
                            motOk ++;
                        }
                    }
                    pbNiveau.setProgress((int)(motOk/mots.size()*100));
                }
            }
        });

        tvNiveau.setText(categorie.getNom());

        return  nouvelleLigne;
    }
}
