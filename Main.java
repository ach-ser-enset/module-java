import java.util.Scanner;

/**

 * Author: Achraf SERHANI
 * Date: 24/11/2024
 * Time: 20:44
 * Project: Exercice 1
 */

public class Main {

    private static int[] codesProduits = new int[100];
    private static String[] nomProduits = new String[100];
    private static int[] quantites = new int[100];
    private static double[] prix = new double[100];

    public static void printMenu() {

        System.out.println("----- Gestion de Stock -----");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.println();
        System.out.println("Choisissez une option :");
    }

    public static void ajouterProduit(int code, String nom, int qantite, double price) {
        if (!verifierProduitExistant(code)) {
            int index = 0;
            for (int i = 0; i < 100; i++) {
                if (codesProduits[i] == 0) {
                    index = i;
                }
            }
            codesProduits[index] = code;
            nomProduits[index] = nom;
            quantites[index] = qantite;
            prix[index] = price;

        } else {
            System.out.println("Le produit " + code + " existe deja dans l'inventaire ");
        }
    }


    public static void modifierProduit(int code, String nom, int nouvelleQuantite, double nouveauPrix) {
        if (verifierProduitExistant(code)) {
            int index = trouverIndexProduit(code);
            if (index != -1) {
                codesProduits[index] = code;
                nomProduits[index] = nom;
                quantites[index] = nouvelleQuantite;
                prix[index] = nouveauPrix;
            } else {
                System.out.println("Code non-trouve");
            }
        } else {
            System.out.println("Le produit n'existe pas ! \n");
        }

    }

    private static int trouverIndexProduit(int code) {
        for (int i = 0; i < 100; i++) {
            if (codesProduits[i] == code) {
                return i;
            }
        }
        return -1;
    }

    public static int supprimerProduit(int code) {

        int index = trouverIndexProduit(code);
        if (index != -1) {
            codesProduits[index] = 0;
            nomProduits[index] = null;
            quantites[index] = 0;
            prix[index] = 0;
        } else  {
            System.out.println("Le produit n'existe pas ! \n");
        }
        return index;
    }

    public static void afficherProduits() {

        System.out.println("**** La Liste Des Produits ***\n");
        for (int i = 0; i < 100; i++) {
            if (codesProduits[i] != 0) {
                afficherUnSeulProduit(codesProduits[i], nomProduits[i], quantites[i], prix[i]);
            }
        }
        System.out.println("*****************\n");
    }

    private static void afficherUnSeulProduit(int code, String nomProduit, int quantite, double prix) {
        System.out.printf("Produit(Code: %d, Nom De Produit = %s, Quantite= %d, Prix=%.02f) %n \n", code, nomProduit, quantite, prix);
    }

    public static void rechercherProduit(String nom) {
        int index = 0;
        for (int i = 0; i < 100; i++) {
            if (nomProduits[i].equals(nom)) {
                index = i;
            }
        }
        afficherUnSeulProduit(codesProduits[index], nomProduits[index], quantites[index], prix[index]);
    }

    public static void totalProduits() {
        double sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += prix[i];
        }
        System.out.println("Total = " + sum);
    }

    // j'ai ajouter la verification si on a un produit deja existant
    private static boolean verifierProduitExistant(int code) {
        for (int i = 0; i < 100; i++) {
            if (codesProduits[i] == code) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Ajouter le code du produit: ");
                    int code = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

                    System.out.println("Ajouter le nom du produit: ");
                    String nomProduit = scanner.nextLine(); // Lire la ligne complète, incluant les espaces

                    System.out.println("Ajouter la quantité : ");
                    int quantite = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

                    System.out.println("Ajouter le prix : ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine(); // Consommer la nouvelle ligne après nextDouble()

                    ajouterProduit(code, nomProduit, quantite, prix);

                    break;

                case 2:
                    System.out.println("Code du produit pour modifier ");
                    int codeProduit = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

                    System.out.println("Ajouter le nom du produit: ");
                    String nouveauNom = scanner.nextLine(); // Lire la ligne complète, incluant les espaces

                    System.out.println("Ajouter la quantité : ");
                    int nouvelleQuantite = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

                    System.out.println("Ajouter le prix : ");
                    double nouveauPrix = scanner.nextDouble();
                    scanner.nextLine(); // Consommer la nouvelle ligne après nextDouble()

                    modifierProduit(codeProduit, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;

                case 3:
                    System.out.println("Ajouter le code du produit: ");
                    code = scanner.nextInt();
                    supprimerProduit(code);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    System.out.println("Veuillez ecrire le nom du produit: ");
                    String nom = scanner.next();
                    rechercherProduit(nom);
                    break;
                case 6:
                    totalProduits();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Veuillez choisir une option !");
                    break;
            }
        }
    }

}