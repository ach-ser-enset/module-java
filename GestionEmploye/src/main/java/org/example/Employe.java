package org.example;

/**
 * @author achraf
 * @date 2/18/25
 */
public class Employe {
    private int id;
    private String nom;
    private String poste;
    private Double salaire;

    // Constructeur avec tous les attributs
    public Employe(int id, String nom, String poste, Double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

    // Constructeur par default
    public Employe() {

    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPoste() {
        return poste;
    }
    public Double getSalaire() {
        return salaire;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPoste(String poste) {
        this.poste = poste;
    }
    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    //Methode toString pour l'affichages des infos employe
    @Override
    public String toString() {
        return "Employé : [Identifiant: " + id + ", Nom: " + nom + ", Poste: " + poste + ", Salaire: " + salaire + "]\n";
    }

    //Méthode de comaparaison de salaires
    public static int compareParSalaire(Employe employe1, Employe employe2) {
        return employe1.getSalaire().compareTo(employe2.getSalaire());
    }





















}
