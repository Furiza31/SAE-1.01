public class test {
    public static void main(String[] args) {
        int[] tab = {3,1,6};
        double r = maFonction(tab, 3);
        System.out.println(r);
    }
    public static double maFonction(int pfTab[], int pfNbE) {
        int somme    ;
        int i  ;
        double resultat;
        while (i < pfNbE) {
            somme = somme + pfTab[i];
        }
        resultat = (double)somme/pfNbE;
        return resultat;
    }
}
