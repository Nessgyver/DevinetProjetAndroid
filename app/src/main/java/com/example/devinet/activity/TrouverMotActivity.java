package com.example.devinet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
    private String proposition;
    private int index = 0;
    private List<Button> listeBtn = new ArrayList<Button>();
    private List<TextView> listeTv = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouver_mot);

        mvm = ViewModelProviders.of(this).get(MotViewModel.class);
        index = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();

        initialiserListeTv();

        initialiserListeBtn();

        Intent intent = getIntent();
        listeMotsObs = mvm.get(intent.getIntExtra("idCategorie", 1));

        changerMotCourant();
    }

    private void changerIndex() {
        if(index < listeMot.size()-1){
            index ++;
        }else{
            index = 0;
        }
    }

    private void changerMotCourant() {
        listeMotsObs.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                listeMot = listeMotsObs.getValue();
                motCourant = listeMot.get(index);

                initialiserVue();
            }
        });
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
            listeBtn.get(i).setVisibility(View.VISIBLE);
        }
        if(motMelange.length()<5){
            listeBtn.get(4).setVisibility(View.INVISIBLE);
        }
        if(motMelange.length()<6){
            listeBtn.get(5).setVisibility(View.INVISIBLE);
        }

        proposition = motCourant.getProposition();
        remplirCasesProposition();
    }

    /**
     * Méthode permettant de créer un ArrayList des TextView présents dans la vue
     */
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

    /**
     * Méthode permettant de créer un ArrayList des boutons présents dans la vue
     */
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

    /**
     * quand l'utilisateur clique sur un bouton, on récupère sa valeur(lettre affichée)
     * pour l'ajouter à la proposition
     */
    public void onClickAjouterLettre(View view) {
        Button btnLettreCliquee = (Button)view;
        String lettreCliquee = btnLettreCliquee.getText().toString();
        if(proposition == null){
            proposition = lettreCliquee;
        }else{
            proposition += lettreCliquee;
        }
        remplirCasesProposition();
    }

    /**
     * quand l'utilisateur clique sur le bouton effacer, on réinitialise la proposition en bdd
     * et on repart de 0 pour l'affichage de la vue
     */
    public void onClickEffacer(View view) {
        motCourant.setProposition("");
        mvm.update(motCourant);
        initialiserVue();
    }

    /**
     * quand l'utilisateur clique sur le bouton passer,on enregistre l'avancement et on met à jour l'index
     * et on réinitialise l'affichage pour qu'il corresponde au mot suivant dans la liste
     */
    public void onClickPasser(View view) {
        motCourant.setProposition(proposition);
        mvm.update(motCourant);
        changerIndex();
        changerMotCourant();
    }

    /**
     * quand l'utilisateur clique sur le bouton valider, on enregistre sa proposition en bdd
     * si la proposition correspond au mot à trouver, on change l'index
     * et on réinitialise l'affichage pour qu'il corresponde au mot suivant dans la liste
     * sinon on affiche un message d'erreur
     */
    public void onClickValider(View view) {
        motCourant.setProposition(proposition);
        mvm.update(motCourant);
        if(proposition.equals(motCourant.getMot())){
            changerIndex();
            changerMotCourant();
        }else{
            Toast.makeText(this, "Mauvaise Réponse", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * méthode permettant de remplir les TextView où s'affiche la proposition du joueur
     * elle efface les boutons correspondant aux lettres utilisées pour la cohérence de l'affichage
     */
    private void remplirCasesProposition() {
        for (int i = 0; i < 6; i++) {
            if (proposition != null){
                if(i < proposition.length()){
                    String lettre = String.valueOf(proposition.charAt(i));
                    listeTv.get(i).setText(lettre);
                    for (int j = 0; j < motCourant.getMot().length(); j++) {
                        if(listeBtn.get(j).getText().equals(lettre)){
                            listeBtn.get(j).setText("");
                            listeBtn.get(j).setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                }else{
                    listeTv.get(i).setText("");
                }
            }else{
                listeTv.get(i).setText("");
            }
        }
    }
}
