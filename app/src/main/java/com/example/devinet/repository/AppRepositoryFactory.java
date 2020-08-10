package com.example.devinet.repository;

import android.content.Context;

import com.example.devinet.repository.bdd.CategorieBddRepository;
import com.example.devinet.repository.bdd.MotBddRepository;

public abstract class AppRepositoryFactory {

    public static ICategorieRepository getCategorieRepository(Context context){
        return new CategorieBddRepository(context);
    }

    public static IMotRepository getMotRepository(Context context){
        return new MotBddRepository(context);
    }

}
