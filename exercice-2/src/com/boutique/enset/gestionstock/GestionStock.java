package com.boutique.enset.gestionstock;

import java.util.Scanner;

public class GestionStock {
    private static Produit[] produits = new Produit[ 100 ];
    private static int nbProduits = 0; //  cette variable sera utilisée pour suivire le nombre de produits au tableau produits

    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    ajouterProduit(sc);
                    break;

                case 2:
                    modifierProduits(sc);
                    break;

                case 3:
                    supprimerProduit(sc);
                    break;

                case 4:
                    afficherProduits();
                    break;

                case 5:
                    rechercherProduit(sc);
                    break;

                case 6:
                    calculerValeurStock();
                    break;

                case 0:
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Veullez entrer un choix valide !");
            }
        }

    }

    public static void printMenu() {
        System.out.println("\n--- Gestion De Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("\n Choisissez une option : ");
    }

    public static void ajouterProduit(Scanner sc) {
        if (nbProduits >= 100) {
            System.out.println("Erreur : Stock De Produits Est Plein !");
            return;
        }
        System.out.print("Code De Produit : ");
        int code = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nbProduits; i++) {
            if (produits[ i ].getCode() == code) {
                System.out.println("Erreur : Ce produit déjà existant !");
                return;
            }
        }
        System.out.print("Nom De Produit : ");
        String nom = sc.nextLine();
        System.out.print("Quantité De Produit : ");
        int quantite = sc.nextInt();
        System.out.print("Prix De Produit : ");
        double prix = sc.nextDouble();
        produits[ nbProduits++ ] = new Produit(code, nom, quantite, prix);
        System.out.println("Produit ajouté !");
    }

    public static void modifierProduits(Scanner sc) {
        System.out.print("Code du produit à modifier : ");
        int code = sc.nextInt();
        for (int i = 0; i < nbProduits; i++) {
            if (produits[ i ].getCode() == code) {
                sc.nextLine();
                System.out.print("Nouveau nom : ");
                String nNom = sc.nextLine();
                System.out.print("Nouvelle quantité : ");
                int nQuantite = sc.nextInt();
                System.out.print("Nouveau prix : ");
                double nPrix = sc.nextDouble();
                produits[ i ].setNom(nNom);
                produits[ i ].setQuantite(nQuantite);
                produits[ i ].setPrix(nPrix);
                System.out.println("Produit modifié avec succès.");
                return;
            }
        }
        System.out.println("Erreur : Produit non trouvé !");
    }

    public static void supprimerProduit(Scanner sc) {
        System.out.print("Code du produit à supprimer : ");
        int code = sc.nextInt();
        for (int i = 0; i < nbProduits; i++) {
            if (produits[ i ].getCode() == code) {
                produits[ i ] = produits[ --nbProduits ]; // on remplace le produit à supprimé par le dernier produit du tableau, on peut aussi utiliser directement .remove()
                System.out.println("Produit supprimé avec succès.");
                return;
            }
        }
        System.out.println("Erreur : Produit non trouvé !");
    }

    public static void afficherProduits() {
        System.out.println("\n--- La Liste Des Produits En Stock ---\n");
        for (int i = 0; i < nbProduits; i++) {
            System.out.println(produits[ i ]);
        }
        System.out.println("\n-------------------------\n");
    }

    public static void rechercherProduit(Scanner scanner) {
        System.out.print("Nom du produit à rechercher : ");
        String nom = scanner.next();
        System.out.println("\n--- Résultats de Recherche ---");
        for (int i = 0; i < nbProduits; i++) {
            if (produits[ i ].getNom().equalsIgnoreCase(nom)) {
                System.out.println(produits[ i ]);
                return;
            }
        }
        System.out.println("Erreur : Produit non trouvé !");
    }

    public static void calculerValeurStock(){
        double valeurTotale = 0.0;
        for (int i = 0; i < nbProduits; i++) {
            valeurTotale += produits[i].valeurTotale();
        }
        System.out.println("La valeur totale du stock : " + valeurTotale);
    }
}




