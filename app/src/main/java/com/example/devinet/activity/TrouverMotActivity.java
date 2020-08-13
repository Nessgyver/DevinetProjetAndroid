package com.example.devinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devinet.R;
import com.example.devinet.bo.Mot;
import com.example.devinet.view_model.MotViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrouverMotActivity extends AppCompatActivity {

    private MotViewModel mvm = null;
    private LiveData<List<Mot>> listeMotsObs = null;
    private List<Mot> listeMot = null;
    private Mot motCourant = null;

    private List<Button> listeBtn = new ArrayList<Button>();
    private List<TextView> listeTv = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouver_mot);

        mvm = ViewModelProviders.of(this).get(MotViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initialiserListeTv();

        initialiserListeBtn();

        Intent intent = getIntent();
        listeMotsObs = mvm.get(intent.getIntExtra("idCategorie", 1));
        listeMotsObs.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                listeMot = listeMotsObs.getValue();
                Log.i("DROU", String.valueOf(listeMot.size()));
                motCourant = listeMot.get(0);

                initialiserVue();
            }
        });
    }

    public void onClickAjouterLettre(View view) {
        View parentRow = (View) view.getParent();
        Button btnLettreCliquee = (Button) parentRow.getParent();
        String lettreCliquee = btnLettreCliquee.getText().toString();
        Toast.makeText(this, lettreCliquee, Toast.LENGTH_SHORT).show();
    }

    public void onClickPasser(View view) {
        Toast.makeText(this, "onClickPasser", Toast.LENGTH_SHORT).show();
    }

    public void onClickValider(View view) {
        Toast.makeText(this, "onClickValider", Toast.LENGTH_SHORT).show();
    }

    private void initialiserVue(){
        //on mélange les lettre du mot et on les place dans un List<Character>
        List<Character> lettres = new ArrayList<Character>();
        Random r = new Random();
        int index;
        String mot = motCourant.getMot();
        for(int i = 0; i<mot.length(); i++){
            index = r.nextInt(lettres.size()+1);
            lettres.add(index, mot.charAt(i));
        }

        //on récupère les lettres ainsi mélanger pour former un nouveau mot
        StringBuffer motMelange = new StringBuffer();
        for (int i = 0; i < lettres.size(); i++) {
            motMelange.append(lettres.get(i));
        }

        //on alimente les boutons pour que le joueur puisse s'en servir pour proposer une solution
        for (int i = 0; i < motMelange.length(); i++) {
            listeBtn.get(i).setText(String.valueOf(motMelange.charAt(i)));
        }
        if(motMelange.length()<5){
            listeBtn.get(4).setVisibility(View.INVISIBLE);
        }
        if(motMelange.length()<6){
            listeBtn.get(5).setVisibility(View.INVISIBLE);
        }

        String proposition = motCourant.getProposition();
        remplirCasesProposition(proposition);
    }

    private void remplirCasesProposition(String proposition) {
        for (int i = 0; i < 6; i++) {
            if (proposition != null){
                if(i < proposition.length()){
                    listeTv.get(i).setText(String.valueOf(proposition.charAt(i)));
                }else{
                    listeTv.get(i).setText("");
                }
            }else{
                listeTv.get(i).setText("");
            }
        }
    }

    private void initialiserListeTv() {
        TextView tv1 = findViewById(R.id.tv_reponse_1);
        TextView tv2 = findViewById(R.id.tv_reponse_2);
        TextView tv3 = findViewById(R.id.tv_reponse_3);
        TextView tv4 = findViewById(R.id.tv_reponse_4);
        TextView tv5 = findViewById(R.id.tv_reponse_5);
        TextView tv6 = findViewById(R.id.tv_reponse_6);
        listeTv.add(tv1);
        listeTv.add(tv2);
        listeTv.add(tv3);
        listeTv.add(tv4);
        listeTv.add(tv5);
        listeTv.add(tv6);
    }

    private void initialiserListeBtn() {
        Button btnReponse1 = findViewById(R.id.btn_reponse_1);
        Button btnReponse2 = findViewById(R.id.btn_reponse_2);
        Button btnReponse3 = findViewById(R.id.btn_reponse_3);
        Button btnReponse4 = findViewById(R.id.btn_reponse_4);
        Button btnReponse5 = findViewById(R.id.btn_reponse_5);
        Button btnReponse6 = findViewById(R.id.btn_reponse_6);
        listeBtn.add(btnReponse1);
        listeBtn.add(btnReponse2);
        listeBtn.add(btnReponse3);
        listeBtn.add(btnReponse4);
        listeBtn.add(btnReponse5);
        listeBtn.add(btnReponse6);
    }
}
