package tsp.pro3600;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Création d'un objet Symflow
        Symflow hopital = new Symflow("Hôpital");

        // Création des nœuds
        Node etape1 = new Node(0, "etape1");
        Node etape2_1 = new Node(etape1, 0, "etape2-1");
        Node etape2_2 = new Node(etape1, 0, "etape2-2");
        And etape2 = new And(0, "etape2", etape2_1, etape2_2);
        Node etape3 = new Node(etape2, 0, "etape3");
        Node etape4_1 = new Node(etape1, 0, "etape4-1");
        Node etape4_2 = new Node(etape1, 0, "etape4-2");
        Node etape4 = new Or(0,"etape4", etape4_1, etape4_2);


        // Ajout des nœuds au projet
        hopital.addNode_initial(etape1);
        hopital.addNode_initial(etape2);
        hopital.addNode_initial(etape3);
        hopital.addNode_initial(etape4);

        // Affichage de la liste des nœuds
        System.out.println(hopital);
        //pour l'afffichage brut :
        System.out.println(hopital.toStringRaw());

        // Affichage de la structure du projet sous forme d'arbre
        System.out.println();
        hopital.displayProjectTree();

        // Simulation du parcours de l'arbre
        System.out.println();
        System.out.println("Simulation du parcours de l'arbre...");

        // Copie de la liste project pour éviter ConcurrentModificationException
        ArrayList<Node> projectCopy = new ArrayList<>(hopital.getProject_initial());
        for (Node node : projectCopy) {
            node.setState(1); // Mettre à jour l'état du nœud

            // Traiter le nœud en fonction de son type
            if (node instanceof And) {
                hopital.processAndNode((And) node); // Traitement pour un nœud de type And
                //(And) node pour convertir le nœud node en un objet de type And. Cela permet d'appeler la méthode processAndNode spécifique à la classe And sur cet objet.
            } else if (node instanceof Or) {
                hopital.processOrNode((Or) node); // Traitement pour un nœud de type Or
            }
        }

        // Affichage de la nouvelle liste des nœuds
        System.out.println("Nouvelle liste des nœuds après le parcours de l'arbre :");
        System.out.println(hopital);
    }
}