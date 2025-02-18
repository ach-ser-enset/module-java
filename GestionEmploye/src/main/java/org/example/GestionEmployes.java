package org.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author achraf
 * @date 2/18/25
 */
public class GestionEmployes {
    private static final int maxEmployes = 50; // le nombre max des employes
    private static Employe[] employes = new Employe[ maxEmployes ]; // le tableau pour stocker les employes
    private static int nbrEmployes = 0; // compteur pour le nombre des employes

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;
        ajouterEmployesAutomatiquement(); // j'ai ajouter cette methode pour ajouter des employés automatiquement sera utilisé pour la demo

        // Gestion les interactions avec l'utilisateur
        do {
            printMenu();
            System.out.print("choisissez une action : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterEmploye(scanner);
                    break;
                case 2:
                    modifierEmploye(scanner);
                    break;
                case 3:
                    supprimerEmploye(scanner);
                    break;
                case 4:
                    afficherEmploye();
                    break;
                case 5:
                    rechercherEmploye(scanner);
                    break;
                case 6:
                    calculerMasseSalariale();
                    break;
                case 7:
                    trierEmployeParSalaire(scanner);
                    break;
                case 8:
                    System.out.println("Fermeture de l'app");
                    break;
                default:
                    System.out.println("Veuillez tapper un choix valide !");
            }
        } while (choix != 8);
        scanner.close();
    }

    // Affichage de menu principale
    private static void printMenu() {
        System.out.println("\n--- Gestion Des Employés ---");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("8. Quitter");
    }

    //ajouter un employé
    private static void ajouterEmploye(Scanner scanner) {
        if (nbrEmployes >= maxEmployes) {
            System.out.println("Impossible d'ajouter un nouvel employé.");
            return;
        }
        System.out.print("Entrez l'ID de l'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez le nom de l'employé : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le poste de l'employé : ");
        String poste = scanner.nextLine();

        System.out.print("Entrez le salaire de l'employé : ");
        double salaire = scanner.nextDouble();

        Employe employe = new Employe(id, nom, poste, salaire);
        employes[ nbrEmployes++ ] = employe;
        System.out.println("Employé ajouté avec succès !");
    }

    // Modier un employé
    private static void modifierEmploye(Scanner scanner) {
        System.out.print("Entrez l'ID de l'employé à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[ i ].getId() == id) {
                System.out.print("Entrez le nouveau nom : ");
                String nouveauNom = scanner.nextLine();

                System.out.print("Entrez le nouveau poste : ");
                String nouveauPoste = scanner.nextLine();

                System.out.print("Entrez le nouveau salaire : ");
                double nouveauSalaire = scanner.nextDouble();

                employes[ i ].setNom(nouveauNom);
                employes[ i ].setPoste(nouveauPoste);
                employes[ i ].setSalaire(nouveauSalaire);
                System.out.println("Employé modifié avec succès !");
                return;
            }
        }

        System.out.println("Aucun employé trouvé !");
    }

    // Supprimer un employé
    private static void supprimerEmploye(Scanner scanner) {
        System.out.print("Entrez l'ID de l'employé à supprimer : ");
        int id = scanner.nextInt();

        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[ i ].getId() == id) {
                for (int j = i; j < nbrEmployes - 1; j++) {
                    employes[ j ] = employes[ j + 1 ];
                }
                nbrEmployes--;
                System.out.println("Employé supprimé avec succès !");
                return;
            }
        }

        System.out.println("Aucun employé trouvé !");
    }

    // Afficher la liste des employés
    private static void afficherEmploye() {
        if (nbrEmployes == 0) {
            System.out.println("Aucun employé à afficher.");
            return;
        }

        for (int i = 0; i < nbrEmployes; i++) {
            System.out.println(employes[ i ]);
        }
    }

    // Rechercher un employé soit par nom, soit par poste
    private static void rechercherEmploye(Scanner scanner) {
        System.out.print("Entrez le nom ou le poste à rechercher : ");
        String critere = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < nbrEmployes; i++) {
            if (employes[ i ].getNom().equalsIgnoreCase(critere) || employes[ i ].getPoste().equalsIgnoreCase(critere)) {
                System.out.println(employes[ i ]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Aucun employé trouvé !");
        }
    }

    // Calcule de masse salariale
    private static void calculerMasseSalariale() {
        double masseSalariale = 0;
        for (int i = 0; i < nbrEmployes; i++) {
            masseSalariale += employes[ i ].getSalaire();
        }
        System.out.println("La masse salariale est : " + masseSalariale);
    }

    // Trier les employés par salaire
    private static void trierEmployeParSalaire(Scanner scanner) {
        System.out.println("Choisissez l'ordre de tri :");
        System.out.println("1. Croissant");
        System.out.println("2. Décroissant");
        System.out.print("Entrez votre choix (1 ou 2) : ");
        int choix = scanner.nextInt();

        if (choix == 1 || choix == 2) {
            Arrays.sort(employes, 0, nbrEmployes, (e1, e2) -> {
                if (choix == 1) {
                    return Employe.compareParSalaire(e1, e2); // ordre croissant
                } else {
                    return Employe.compareParSalaire(e2, e1); // ordre décroissant
                }
            });

            System.out.println("Employés triés avec succès !");
            afficherEmploye();
        } else {
            System.out.println("Veuillez entrer un choix valide !");
        }
    }
    
    // Methode pour ajouter des employes automatiquement dans le tableau, sera utiliser pour la demo 
    public static void ajouterEmployesAutomatiquement() {
        employes[nbrEmployes++] = new Employe(1, "Youssef El Amrani", "Développeur", 11000.00);
        employes[nbrEmployes++] = new Employe(2, "Fatima Zahra Benali", "Manager", 25000.00);
        employes[nbrEmployes++] = new Employe(3, "Mohamed Chakir", "Designer", 15000.00);
        employes[nbrEmployes++] = new Employe(4, "Amina El Fassi", "Développeur", 12000.00);
        employes[nbrEmployes++] = new Employe(5, "Karim Bouzidi", "Testeur", 9500.00);
        employes[nbrEmployes++] = new Employe(6, "Leila Mansouri", "Développeur", 13000.00);
        employes[nbrEmployes++] = new Employe(7, "Hassan El Ouazzani", "Designer", 16000.00);
        employes[nbrEmployes++] = new Employe(8, "Nadia El Kadi", "Manager", 24000.00);
        employes[nbrEmployes++] = new Employe(9, "Omar El Hajji", "Testeur", 10000.00);
        employes[nbrEmployes++] = new Employe(10, "Sanaa El Moussaoui", "Développeur", 14000.00);
        employes[nbrEmployes++] = new Employe(11, "Rachid El Filali", "Designer", 17000.00);
        employes[nbrEmployes++] = new Employe(12, "Zineb El Idrissi", "Manager", 23000.00);
        employes[nbrEmployes++] = new Employe(13, "Ahmed El Khatib", "Testeur", 10500.00);
        employes[nbrEmployes++] = new Employe(14, "Samira El Gharbi", "Développeur", 14500.00);
        employes[nbrEmployes++] = new Employe(15, "Mehdi El Fahsi", "Designer", 17500.00);
        employes[nbrEmployes++] = new Employe(16, "Houda El Mansouri", "Manager", 22000.00);
        employes[nbrEmployes++] = new Employe(17, "Younes El Boukili", "Testeur", 11000.00);
        employes[nbrEmployes++] = new Employe(18, "Khadija El Harrak", "Développeur", 15000.00);
        employes[nbrEmployes++] = new Employe(19, "Adil El Amraoui", "Designer", 18000.00);
        employes[nbrEmployes++] = new Employe(20, "Noura El Khayat", "Manager", 21000.00);
        employes[nbrEmployes++] = new Employe(21, "Said El Mernissi", "Testeur", 11500.00);
        employes[nbrEmployes++] = new Employe(22, "Asmae El Hassani", "Développeur", 15500.00);
        employes[nbrEmployes++] = new Employe(23, "Hamza El Ghannam", "Designer", 18500.00);
        employes[nbrEmployes++] = new Employe(24, "Imane El Ouafi", "Manager", 20000.00);
        employes[nbrEmployes++] = new Employe(25, "Anas El Kabbaj", "Testeur", 12000.00);
        employes[nbrEmployes++] = new Employe(26, "Soukaina El Haddad", "Développeur", 16000.00);
        employes[nbrEmployes++] = new Employe(27, "Bilal El Moustaoui", "Designer", 19000.00);
        employes[nbrEmployes++] = new Employe(28, "Hafsa El Khamlichi", "Manager", 19500.00);
        employes[nbrEmployes++] = new Employe(29, "Yassine El Azizi", "Testeur", 12500.00);
        employes[nbrEmployes++] = new Employe(30, "Salma El Moutawakil", "Développeur", 16500.00);
        employes[nbrEmployes++] = new Employe(31, "Reda El Fassi", "Designer", 19500.00);
        employes[nbrEmployes++] = new Employe(32, "Meryem El Khadiri", "Manager", 19000.00);
        employes[nbrEmployes++] = new Employe(33, "Tarik El Bouhali", "Testeur", 13000.00);
        employes[nbrEmployes++] = new Employe(34, "Rania El Moussaoui", "Développeur", 17000.00);
        employes[nbrEmployes++] = new Employe(35, "Walid El Ghazouani", "Designer", 20000.00);
        employes[nbrEmployes++] = new Employe(36, "Naima El Bouzidi", "Manager", 18500.00);
        employes[nbrEmployes++] = new Employe(37, "Ismail El Kettani", "Testeur", 13500.00);
        employes[nbrEmployes++] = new Employe(38, "Lina El Farissi", "Développeur", 17500.00);
        employes[nbrEmployes++] = new Employe(39, "Hicham El Moutaouakil", "Designer", 20500.00);
        employes[nbrEmployes++] = new Employe(40, "Sara El Kabbaj", "Manager", 18000.00);
        employes[nbrEmployes++] = new Employe(41, "Fouad El Hachimi", "Testeur", 14000.00);
        employes[nbrEmployes++] = new Employe(42, "Nabila El Ouazzani", "Développeur", 18000.00);
        employes[nbrEmployes++] = new Employe(43, "Khalid El Mernissi", "Designer", 21000.00);
        employes[nbrEmployes++] = new Employe(44, "Hajar El Harrak", "Manager", 17500.00);
        employes[nbrEmployes++] = new Employe(45, "Marouane El Fahsi", "Testeur", 14500.00);
        employes[nbrEmployes++] = new Employe(46, "Ghita El Idrissi", "Développeur", 18500.00);
        employes[nbrEmployes++] = new Employe(47, "Othmane El Boukili", "Designer", 21500.00);
        employes[nbrEmployes++] = new Employe(48, "Chaimae El Kadi", "Manager", 17000.00);
        employes[nbrEmployes++] = new Employe(49, "Yassir El Khatib", "Testeur", 15000.00);
        
    }


}
