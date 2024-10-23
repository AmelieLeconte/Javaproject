package tsp.pro3600;
import java.util.ArrayList;
import java.util.Scanner;

public class Symflow {
    private String name; // Nom du du Symflow qui est un "arbre" constitué de Nodes à traiter
    private ArrayList<Node> project_initial; // ArrayList contenant tous les Nodes à traiter
    private ArrayList<Node> project_final; // ArrayList contenant tous les Nodes traités


    // Constructeur du Symflow
    public Symflow(String name) {
        this.name = name;
        this.project_initial = new ArrayList<>();
        this.project_final = new ArrayList<>();

    }

    public ArrayList<Node> getProject_initial() {
        return project_initial;
    }

    public ArrayList<Node> getProject_final() {
        return project_final;
    }

    public void addNode_initial(Node node) {
        project_initial.add(node);
    }

    public void addNode_final(Node node) {
        project_final.add(node);
    }

    // Méthode indiquant l'ordre dans lequel on va traiter les Nodes contenus dans l'ArrayList parallels
    public ArrayList<Node> orderAnd(ArrayList<Node> parallels) {
        Node left = parallels.get(0);
        Node right = parallels.get(1);
        ArrayList<Node> orderAnd = new ArrayList<>(); // Création d'une liste contenant les Nodes de parallels mais qui sera ordonnée de la façon indiquée par l'utilisateur

        Scanner scanner = new Scanner(System.in); // Création d'un scanner permettant de demander à l'utilisateur l'ordre dans lequel effectuer les Nodes de parallels
        try {
            System.out.println("Veuillez choisir une priorité entre " + right.getName() + " et " + left.getName());
            String choix = scanner.nextLine(); // Lecture de la réponse de l'utilisateur
            System.out.println("Vous avez choisi en priorité : " + choix);

            if (choix.equals(right.getName())) { // Si l'utilisateur choisit d'effectuer le Node de droite en premier,
                orderAnd.add(right); // le Node right est d'abord ajouté à la liste des noeuds ordonnée qui est nommée orderAnd
                orderAnd.add(left); // puis le Node left est ajouté en deuxième. il sera donc traité en dernier.
            } else if (choix.equals(left.getName())) { // Cas contraire : l'utilisateur souhaite effectuer le Node de gauche en premier
                orderAnd.add(left);
                orderAnd.add(right);
            } else {
                System.out.println("Choix invalide"); // Si le nom de noeud donné par l'utilisateur ne correspond pas au noeud de droite ni au noeud de gauche
                System.exit(1); // il y a alors une erreur
            }
            return orderAnd;
        } finally {
            scanner.close();
        }
    }

    // Méthode indiquant le Node selectionné par l'utilisateur parmis les Nodes contenus dans l'ArrayList choices
    public Node selectedNode(ArrayList<Node> choices) {
        Node left = choices.get(0);
        Node right = choices.get(1);
        Scanner scanner = new Scanner(System.in); // Création d'un scanner permettant de demander à l'utilisateur quel Node effectuer parmis les Nodes de choices
        try {
            System.out.println("Veuillez choisir une étape entre " + right.getName() + " et " + left.getName());
            String choix = scanner.nextLine();
            System.out.println(this.name);
            System.out.println("Vous avez choisi d'effectuer l'étape : " + choix);

            if (choix.equals(right.getName())) { // Cas où l'utilisateur choisit d'effectuer le Node de droite
                return right;
            } else if (choix.equals(left.getName())) { // Cas où l'utilisateur choisit d'effectuer le Node de gauche
                return left;
            } else {
                System.out.println("Choix invalide");
                System.exit(1);
                return null; // Ajout d'un retour par défaut (ceci n'est atteint que pour éviter une erreur de compilation)
            }
        } finally {
            scanner.close();
        }
    }

    // Méthode permettant d'effectuer une étape de Or i.e. un Node de type Or
    public void processOrNode(Or orNode) {
        Node previousNode = orNode.getParent1(); // On enregiste le Node situé avant de Node de type Or afin qu'il devienne le parent des nodes de choices
        ArrayList<Node> choices = orNode.getChoices(); // Récupérer les choix possibles
        Node selectedNode = selectedNode(choices); // Appeler la méthode selectedNode avec les choix possibles
        selectedNode.setParent1(previousNode); // On exécute la méthode demandant à l'utilisateur quel Node effectuer
        addNode_final(selectedNode); // puis on l'ajoute au projet final
    }

    // Méthode permettant d'effectuer une étape de And i.e. un Node de type And
    public void processAndNode(And andNode) {
        ArrayList<Node> order = orderAnd(andNode.getParallels());
        Node previousNode = andNode.getParent1(); // On enregiste le Node situé avant de Node de type And afin qu'il devienne le parent des nodes de parallels
        for (Node parallel : order) {
            parallel.setParent1(previousNode);
            parallel.setParent2(null);
            previousNode = parallel;
        }
        for (Node node : order) {
            addNode_final(node);
        }
    }

    // Méthode pour obtenir la représentation brute de la liste
    public String toStringRaw() {
        StringBuilder sb = new StringBuilder();
        sb.append("Affichage brut : [");
        for (Node node : getProject_initial()) {
            if (node instanceof And || node instanceof Or) {
                sb.append("[");
                ArrayList<Node> parents;
                if (node instanceof And) {
                    parents = ((And) node).getParallels();
                } else {
                    parents = ((Or) node).getChoices();
                }
                for (Node child : parents) {
                    sb.append(child.getName()).append(", ");
                }
                if (!parents.isEmpty()) {
                    sb.setLength(sb.length() - 2); // Supprime la virgule et l'espace après le dernier élément
                }
                sb.append("]");
            } else {
                sb.append(node.getName());
            }
            sb.append(", ");
        }
        if (!getProject_initial().isEmpty()) {
            sb.setLength(sb.length() - 2); // Supprime la virgule et l'espace après le dernier élément
        }
        sb.append("]");
        return sb.toString();
    }

    // Méthode pour obtenir une représentation lisible de la liste
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Affichage joli : \n Projet: ").append(name).append("\n");
        sb.append("Liste des noeuds:\n");
        for (Node node : getProject_initial()) {
            sb.append(node.toString()).append("\n");
        }
        return sb.toString();
    }


    // Méthode récursive pour afficher un nœud et ses enfants avec une indentation correspondant à la profondeur dans l'arbre
    public void displayProjectTree() {
        System.out.println("Structure du projet " + this.name + ":");
        for (Node node : getProject_initial()) {
            displayNodeTree(node, 0); // Commencer à afficher l'arbre à partir de ce nœud avec une indentation de profondeur 0
        }
    }

    // Méthode récursive pour afficher un nœud et ses enfants avec une indentation correspondant à la profondeur dans l'arbre
    private void displayNodeTree(Node node, int depth) {

        // Si le nœud est de type And ou Or, afficher ses enfants avec les mots "AND" ou "OR"
        if (node instanceof And) {
            And andNode = (And) node;
            ArrayList<Node> parallels = andNode.getParallels();
            for (int i = 0; i < parallels.size(); i++) {
                Node parallel = parallels.get(i);
                System.out.print(parallel.getName() + " [" + parallel.getState() + "]");
                if (i < parallels.size() - 1) {// Ligne utile lorsqu'on a plus de deux Nodes dans choices,
                                            // mais par hypothèse ce n'est pas le cas ici
                    System.out.print(" AND ");
                }
            }
        }
        else if (node instanceof Or) {
            Or orNode = (Or) node;
            ArrayList<Node> choices = orNode.getChoices();
            for (int i = 0; i < choices.size(); i++) {
                Node choice = choices.get(i);
                System.out.print(choice.getName() + " [" + choice.getState() + "]");
                if (i < choices.size() - 1) { // Ligne utile lorsqu'on a plus de deux Nodes dans choices,
                                        // mais par hypothèse ce n'est pas le cas ici
                    System.out.print(" OR ");
                }
            }
        }
        else {
            // Afficher le nom du nœud et son état
            System.out.print("           " + node.getName() + " [" + node.getState() + "]");
        }
        System.out.println(); // Passer à la ligne après avoir affiché le Node
    }

}