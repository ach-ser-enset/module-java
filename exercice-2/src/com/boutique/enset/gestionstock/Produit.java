package com.boutique.enset.gestionstock;

public class Produit {
    //declaration des attributs de la classe Produit
    public int code;
    public String nom;
    public int quantite;
    public double prix;

    // constructeur avec tous les attributs
    public Produit(int code, String nom, int quantite, double prix){
        this.code = code;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }

    // Constructeur par défaut
    public Produit(){
        this(0,"",0,0);
    }

    // getter&setter code
    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }

    // getter&setter nom
    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom =nom;
    }

    // getter&setter quantité
    public int getQuantite(){
        return quantite;
    }
    public void setQuantite(int quantite){
        this.quantite = quantite;
    }

    // getter&setter prix
    public double getPrix(){
        return prix;
    }
    public void setPrix(double prix){
        this.prix = prix;
    }

    // la method toString pour l'affichage des details du produit
    public String toString(){
        return "Produit [ Code: " + code + ", Nom: " + nom + ", Quantité: " + quantite + ", Prix: " + prix + " ]";
    }

    // la methode pour calculer la valeur totale d'un produit
    public double valeurTotale(){
        return quantite * prix;
    }

}
