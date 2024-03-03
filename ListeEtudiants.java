import java.io.*;
import java.util.Scanner;

public class ListeEtudiants {

    /**
     * Donne le nombre d’étudiants de la liste pfListe
     * 
     * @param pfListe IN tableau contenant la liste d'étudiants nom, prenom
     * @return le nombre d’étudiants de la liste
     **/
    public static int nbEtudiant(String pfListe[][]) {
        return pfListe.length;
    }

    /**
     * Charge dans un tableau la liste des étudiants
     * 
     * @param pfFileName  IN le nom du fichier à lire
     * @param pfDelimiter IN le délimiteur de champs dans le fichier csv
     * @return le tableau
     **/
    public static String[][] getListe(String pfFileName, String pfDelimiter) throws FileNotFoundException {

        // Ouvre un fichier et compte le nombre de lignes du fichier.
        // Ce nombre de lignes correspond au nombre d'étudiants
        BufferedReader read = new BufferedReader(new FileReader(pfFileName));
        int nbElt = 0;

        // le try catch est la pour recuperer des erreurs eventuelles de lecture
        // dans le fichier. Si une erreur se produit, ce sont les instructions
        // du catch qui seront executees (sera vu en semaine 46).
        try {
            while (read.readLine() != null) {
                nbElt++;
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println("nombre de lignes/etudiants : " + nbElt);

        // création d'un tableau à deux entrees (une pour le nom, une pour le
        // prenom) pour le nbElt etudiants
        String res[][] = new String[nbElt][2];

        // lecture du fichier pour récupérer les noms et prénoms
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfFileName));
            int cpt = 0; // numero de l'etudiant en lecture

            // System.out.println("nombre de lignes : " + nbElt);

            // loops through every line until null found
            while ((line = reader.readLine()) != null) {

                // separate every token by comma
                String[] token = line.split(pfDelimiter);

                res[cpt][0] = token[0];
                res[cpt][1] = token[1];

                cpt++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void main(String[] args) {
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier
            // et le séparateur choisi conforme au contenu du csv
            String promo[][] = getListe("listenomssansaccent.csv", ",");

            // Cette fonction retourne un tableau contenant les noms et prenoms
            // des etudiants de la promotion. Ce tableau est stocke dans promo
            for (int i = 0; i < nbEtudiant(promo); i++) {
                System.out.println("etu : " + promo[i][0] + " " + promo[i][1]);
            }
            System.out.println("Il y a : " + nbEtudiant(promo) + " personnes.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

}