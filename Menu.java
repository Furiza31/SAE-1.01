import java.util.Scanner;

public class Menu {

    // texte du menu
    public static String[][] promo = null;
    // public static int nbItemMenu = 3;
    public static String texteMenu = "Tirer au hasard un·e étudiant·e ? [O/n]";
    public static int nbValIndicesTirables = 0;

    /**
     * fait un tirage aléatoire parmi la liste des personnes tirables
     * 
     * @param pfIndicesTirables   IN/OUT tableau contenant la liste des indices des
     *                            étudiants tirables dans la promo
     * @param pfDernierIndiceTire IN dernier indice tiré
     * @return indice de l'étudiant correspondant aux critères dans la promo
     **/
    public static int traiterChoix(int[] pfIndicesTirables, int pfDernierIndiceTire) {
        int random = (int) (Math.random() * (nbValIndicesTirables + 1));
        nbValIndicesTirables = pop(pfIndicesTirables, nbValIndicesTirables, random);

        if (nbValIndicesTirables == 0) {
            resetIndicesTirables(pfIndicesTirables);
            // nbValIndicesTirables = pop(pfIndicesTirables, nbValIndicesTirables,
            // pfDernierIndiceTire);
            nbValIndicesTirables = pfIndicesTirables.length;
        }
        return random;
    }

    /**
     * affiche le menu et exécute les choix...
     * 
     * @param pfIndicesTirables IN/OUT tableau contenant la liste des indices des
     *                          étudiants tirables dans la promo
     **/
    public static void menu(int[] pfIndicesTirables) {
        String etu[] = new String[2];
        String choixUtilisateurice;
        Scanner clavier = new Scanner(System.in);
        boolean stop = false;
        int indice;
        int dernierIndiceTire = -1;

        while (!stop) {
            System.out.println(texteMenu);
            choixUtilisateurice = clavier.next();
            choixUtilisateurice = choixUtilisateurice.toLowerCase();

            try {
                switch (choixUtilisateurice) {
                case "":
                case "o":
                case "oui":
                case "\n":
                    indice = traiterChoix(pfIndicesTirables, dernierIndiceTire);
                    etu = promo[pfIndicesTirables[indice]];
                    System.out.println(etu[1] + " " + etu[0]);
                    dernierIndiceTire = indice;
                    break;
                case "n":
                case "non":
                    System.out.println("AU REVOIR ...   ...\n");
                    stop = true;
                    break;
                    
                default:
                    System.out.println("\n\n\nBIZARRE ... \n\n\n");
                }

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
        clavier.close();
    }

    public static void main(String arguments[]) {

        try {
            // appel du sous programme précédé du nom de la classe où elle est définie
            // promo = ListeEtudiants.getListe("listenomssansaccent.csv", ",");
            promo = ListeEtudiants.getListe("taras.csv", ",");

            // création et initialisation du tableau des indices des étudiants pouvant être
            // tirés

            int[] indicesTirables = null;
            indicesTirables = new int[ListeEtudiants.nbEtudiant(promo)];
            resetIndicesTirables(indicesTirables);
            // nombre de valeurs utilisées dans indicesTirables
            nbValIndicesTirables = indicesTirables.length;

            System.out.println("Il y a : " + ListeEtudiants.nbEtudiant(promo) + " etudiants");
            menu(indicesTirables);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /**
     * remet
     * 
     * @param vous pouvez en ajouter
     **/
    public static void resetIndicesTirables(int[] pfIndicesTirables) {
        // reset de indicesTirables
        for (int i = 0; i < pfIndicesTirables.length; i++) {
            pfIndicesTirables[i] = i;
        }
    }

    /**
     * supprime la valeur du tableau à l'index
     * 
     * @param pfTab      IN/OUT tableau d'entiers
     * @param pfNbValTab IN nombre de valeurs utilisées du tableau pfTab
     * @param pfIndex    IN indice à laquelle la valeur du tableau est supprimée
     * @return le nouveau nombre de valeurs utilisées du tableau pfTab
     **/
    public static int pop(int[] pfTab, int pfNbValTab, int pfIndex) {
        for (int i = pfIndex; i < pfNbValTab - 1; i++) {
            pfTab[i] = pfTab[i + 1];
        }
        pfNbValTab--;
        return pfNbValTab;
    }
}