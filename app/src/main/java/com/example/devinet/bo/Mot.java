package com.example.devinet.bo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Categorie.class,
        parentColumns = "id",
        childColumns = "idCategorie"))
public class Mot {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String image;
    private String mot;
    private String proposition;
    private int idCategorie;

    @Ignore
    public Mot() {
    }

    @Ignore
    public Mot(String image, String mot, int idCategorie) {
        this.image = image;
        this.mot = mot;
        this.idCategorie = idCategorie;
    }

    public Mot(int id, String image, String mot, String proposition, int idCategorie) {
        this.id = id;
        this.image = image;
        this.mot = mot;
        this.proposition = proposition;
        this.idCategorie = idCategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getProposition() {
        return proposition;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Mot{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", mot='" + mot + '\'' +
                ", proposition='" + proposition + '\'' +
                ", categorie=" + idCategorie +
                '}';
    }
}
