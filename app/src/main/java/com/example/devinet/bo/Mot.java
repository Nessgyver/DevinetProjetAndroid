package com.example.devinet.bo;

public class Mot {
    private int id;
    private String image;
    private String mot;
    private String proposition;
    private Categorie categorie;

    public Mot() {
    }

    public Mot(int id, String image, String mot, String proposition, Categorie categorie) {
        this.id = id;
        this.image = image;
        this.mot = mot;
        this.proposition = proposition;
        this.categorie = categorie;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Mot{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", mot='" + mot + '\'' +
                ", proposition='" + proposition + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
