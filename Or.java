package tsp.pro3600;
import java.util.ArrayList;

public class Or extends Node { // La classe Or hérite de Node car Or est un type particulier de Node qui contient une ArrayList de Nodes parmis lesquels l'utilisateur en choisit un seul
    ArrayList<Node> choices; // ArrayList de Nodes en question

    // Constructeur dans le cas où on n'a qu'un parent
    public Or(Node parent, int state, String name, Node right, Node left) {
        super(parent, state, name);
        this.choices=new ArrayList<Node>();
        this.choices.add(right);
        this.choices.add(left);

    }

    // Constructeur dans le cas initial
    public Or(int state, String name, Node right, Node left) {
        super(state, name);
        this.parent1=null;
        this.parent2=null;
        this.choices=new ArrayList<Node>();
        this.choices.add(right);
        this.choices.add(left);
    }

    // Constructeur à deux parents
    public Or(Node parent1, Node parent2, int state, String name, Node right, Node left) {
        super(parent1, parent2, state, name);
        this.choices=new ArrayList<Node>();
        this.choices.add(right);
        this.choices.add(left);

    }

    // Méthode permettant de renvoyer l'ArrayList de Nodes contenant tous les Nodes parmis lesquels l'utilisateur ne doit en choisir qu'un
    public ArrayList<Node> getChoices() {
        return choices;
    }

}