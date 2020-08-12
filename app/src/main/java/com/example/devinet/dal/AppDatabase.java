package com.example.devinet.dal;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.devinet.bo.Categorie;
import com.example.devinet.bo.Mot;

/**
 * Cette classe représente la base de données de l'application
 */
@Database(entities = {Mot.class, Categorie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract MotDAO getMotDAO();
    public abstract CategorieDAO getCategorieDAO();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "devinet.db")
                    .addCallback(roomFixture)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    private static Callback roomFixture = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            final Categorie quatreLettres = new Categorie(1,"4 lettres");
            final Categorie cinqLettres = new Categorie(2,"5 lettres");
            final Categorie sixLettres = new Categorie(3,"6 lettres");

            new AsyncTask<AppDatabase, Void, Void>(){

                @Override
                protected Void doInBackground(AppDatabase... appDatabases) {
                    // ajoute des Categories dans la base de données
                    CategorieDAO daoCategorie = INSTANCE.getCategorieDAO();
                    daoCategorie.insert(quatreLettres, cinqLettres, sixLettres);

                    Log.i("drou", quatreLettres.toString() );

                    return null;
                }
            }.execute(INSTANCE);

            //ajoutes des mots dans la base de données
            final Mot chat = new Mot(0, "","chat","", quatreLettres.getId());
            final Mot velo = new Mot(0,"", "vélo","vélo", quatreLettres.getId());
            final Mot chien =  new Mot(0,"", "chien","", cinqLettres.getId());
            final Mot tasse = new Mot(0,"", "tasse","stase", cinqLettres.getId());
            final Mot chaise = new Mot(0,"", "chaise","", sixLettres.getId());
            final Mot montre = new Mot(0,"","montre", "",sixLettres.getId());

            new AsyncTask<AppDatabase, Void, Void>(){

                @Override
                protected Void doInBackground(AppDatabase... appDatabases) {
                    // ajoute des Mots dans la base de données
                    MotDAO daoMot = INSTANCE.getMotDAO();
                    daoMot.insert(chat, velo, chien, tasse, chaise, montre);

                    return null;
                }
            }.execute(INSTANCE);
        }
    };

}
