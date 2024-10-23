package tsp.pro3600;
import java.util.ArrayList;

public class And extends Node {
    ArrayList<Node> parallels; // ArrayList contenant deux Nodes. L'utilisateur indiquera ensuite l'ordre dans lequel ils sont traités

    // Constructeur à un parent
    public And(Node parent, int state, String name, Node right, Node left) {
        super(parent, state, name);
        this.parallels=new ArrayList<Node>();
        this.parallels.add(right);
        this.parallels.add(left);

    }

    // Constructeur dans le cas initial
    public And(int state, String name, Node right, Node left) {
        super(state, name);
        this.parent1=null;
        this.parent2=null;
        this.parallels=new ArrayList<Node>();
        this.parallels.add(right);
        this.parallels.add(left);
    }

    // Constructeur à deux parents
    public And(Node parent1, Node parent2, int state, String name, Node right, Node left) {
        super(parent1, parent2, state, name);
        this.parallels=new ArrayList<Node>();
        this.parallels.add(right);
        this.parallels.add(left);

    }

    // Méthode permettant de renvoyer l'ArrayList de Nodes contenant tous les Nodes que l'utilisateur doit effectuer
    public ArrayList<Node> getParallels() {
        return this.parallels;
    }

}