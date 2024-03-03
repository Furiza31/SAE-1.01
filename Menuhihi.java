import java.util.Scanner;

public class Menuhihi {

    // texte du menu
    public static String[][] promo = null;
    // public static int nbItemMenu = 3;
    public static String texteMenu = "Tirer au hasard un·e étudiant·e ? [O/n]";
    public static int nbValTirables = 0;

    /**
     * fait un tirage aléatoire parmi les indices des personnes tirables, et le
     * retire de la liste
     * 
     * @param pfTirables      IN/OUT tableau contenant les indices des étudiants tirables dans la promo
     * @param pfDernierTirage IN précédent tirage
     * @return indice de l'étudiant correspondant aux critères dans la promo
     **/
    public static int traiterChoix(int[] pfTirables, int pfDernierTirage) {
        int tirage = 0;
        if (nbValTirables == 0) {
            // regénération des étudiants à tirer, sans le précédent tirage
            resetTirables(pfTirables);
            nbValTirables = pop(pfTirables, nbValTirables, indexDe(pfDernierTirage, pfTirables));

            // tirage normal
            int random = (int) (Math.random() * nbValTirables);
            tirage = pfTirables[random];
            nbValTirables = pop(pfTirables, nbValTirables, random);

            // on remet dans pfTirables le tirage précédant le tirage ci-dessus
            nbValTirables++; // on crée une case non utilisée à la fin du tableau
            pfTirables[nbValTirables - 1] = pfDernierTirage; // que l'on remplit

            return tirage;
        } else {
            int random = (int) (Math.random() * nbValTirables);
            tirage = pfTirables[random];
            nbValTirables = pop(pfTirables, nbValTirables, random);

            return tirage;
        }
    }

    /**
     * affiche le menu et exécute les choix...
     * 
     * @param pfTirables IN/OUT tableau contenant la liste des indices des étudiants
     *                   tirables dans la promo
     **/
    public static void menu(int[] pfTirables) {
        String choixUtilisateurice;
        Scanner clavier = new Scanner(System.in);
        boolean stop = false;
        int tirage = -1;

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
                    tirage = traiterChoix(pfTirables, tirage);
                    System.out.println(tirage);
                    System.out.println(promo[tirage][1] + " " + promo[tirage][0]);
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

            int[] tirables = null;
            tirables = new int[ListeEtudiants.nbEtudiant(promo)];
            resetTirables(tirables);
            // nombre de valeurs utilisées dans tirables
            // nbValTirables = tirables.length;

            System.out.println("Il y a : " + ListeEtudiants.nbEtudiant(promo) + " etudiants");
            menu(tirables);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /**
     * remet
     * 
     * @param vous pouvez en ajouter
     **/
    public static void resetTirables(int[] pfTirables) {
        // reset de tirables
        nbValTirables = pfTirables.length;
        for (int i = 0; i < pfTirables.length; i++) {
            pfTirables[i] = i;
            System.out.println(pfTirables[i]);
        }
    }

    /**
     * supprime la valeur du tableau à l'index et renvoie le nouveau nombre
     * d'éléments utilisés du tableau
     * 
     * @param pfTab      tableau d'entiers
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

    /**
     * recherche et renvoie l'index de l'élément dans le tableau
     * 
     * @param pfValeur IN élément à trouver
     * @param pfTab    IN tableau recherché
     * @return index de l'élément du tableau recherché dans le tableau
     */
    public static int indexDe(int pfValeur, int[] pfTab) {
        for (int i = 0; i < pfTab.length; i++) {
            if (pfTab[i] == pfValeur) {
                return i;
            }
        }
        return -1;
    }

}