import java.util.ArrayList;
import java.util.List;

public class Node {
    private String aphacode;
    private Node father;
    private List<Node> children;
    private List<Node> bothers;
    private Board board;

    public Node(String aphacode , Node father, Board board) {
        this.aphacode = aphacode;
        this.father = father;

        this.children = this.generateChildren();
        this.bothers = this.generateBrothers();
        this.board = board;
    }

    private List<Node> generateChildren() {
        List<Node> childrens = new ArrayList <Node>();

        return childrens;
    }

    private List<Node> generateBrothers() {
        List<Node> brothers = new ArrayList <Node>();

        return brothers;
    }
}
