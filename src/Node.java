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
        List<Node> childrens = new ArrayList <>();


        return childrens;
    }

    private List<Node> generateBrothers() {
        List<Node> brothers = new ArrayList <Node>();

        return brothers;
    }

    public boolean isFinal() {
        char firstChr = this.aphacode.charAt(0);

        if (firstChr == 'a')
            return false;

        for (char chr : this.aphacode.toCharArray()) {
            if (chr != firstChr)
                return false;
        }

        return true;
    }

    public Node getFather() {
        return father;
    }

    public List <Node> getChildren() {
        return children;
    }

    public List <Node> getBothers() {
        return bothers;
    }

    public Board getBoard() {
        return board;
    }
}
