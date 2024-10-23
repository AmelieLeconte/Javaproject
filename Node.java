package tsp.pro3600;

public class Node {
    Node parent1; // Parent dy noeud dans l'arbre, dans ke cas où le noeud précédent est un noeud simple ou de type Or (un seul Node à effectuer)
    Node parent2; //Deuxième parent du noeud dans l'arbre, dans le cas où le noeud précédent est de type And (deux Nodes à effectuer)
    int state; // state vaut 0, 1 ou 2 lorsque (respectivement) le Node est non réalisé, en cours de réalisation et réalisé
    String name; // Nom du noeud


    // Constructeur dans le cas initial, c.à.d. lorsqu'il n'y a pas encore de parent
    public Node(int state, String name) {
        this.state=state;
        this.name=name;
        this.parent1=null;
        this.parent2=null;
    }

    // Constructeur lorqu'on n'a qu'un parent, c.à.d. lorsque le nœud précédent est un Node simple ou de type Or (un seul nœud à effectuer parmis deux)
    public Node(Node parent, int state, String name) {
        this.parent1=parent;
        this.state=state;
        this.name=name;
    }

    // Constructeur lorsqu'on a deux parents, c.à.d. lorsque le noeud précédent est de type And
    public Node(Node parent1, Node parent2, int state, String name) {
        this.parent1=parent1;
        this.parent2=parent2;
        this.state=state;
        this.name=name;
    }

    // Méthode toString, c'est cette méthode qui permet d'afficher les sous nœuds (et pas juste les nœuds)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node[name=").append(name).append(", state=").append(state).append("]");
        // Gestion des listes or ou and : on regarde si il y a des parents, et si il y en a deux, on affiche les deux
        if (parent1 != null) {
            sb.append(" -> Parent1: ").append(parent1.getName());
        }
        if (parent2 != null) {
            sb.append(" -> Parent2: ").append(parent2.getName());
        }
        return sb.toString();
    }


    // Méthode pour obtenir le parent 1
    public Node getParent1() {
        return this.parent1;
    }

    // Méthode pour obtenir le parent 2
    public Node getParent2() {
        return this.parent2;
    }

    // Méthode pour définir le parent 1
    public void setParent1(Node parent) {
        this.parent1 = parent;
    }

    // Méthode pour définir le parent 2
    public void setParent2(Node parent) {
        this.parent2 = parent;
    }

    public String getName() {
        return this.name;
    }

    public int getState() {
        return state;
    }

    // Méthode pour définir l'état du nœud
    public void setState(int state) {
        this.state = state;
    }
}