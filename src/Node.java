import java.io.Serializable;
import java.util.*;

public class Node implements Serializable {
    private String aphacode;
    private Node father;
    private List <Node> childrens;
    private List <Node> brothers;
    public Map <Node, Integer> cost;
    private Board board;
    private Graph graph;

    public Node(String aphacode, Node father, Board board, Graph graph) {
        this.aphacode = aphacode;
        this.father = father;
        this.board = board;
        this.graph = graph;

        this.brothers = new ArrayList <>();
        this.childrens = new ArrayList <>();
        this.cost = new HashMap <>();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node && ((Node) obj).aphacode.equals(this.aphacode);
    }

    public void generateChildren(List <Node> fifo) {
        if (this.isFinal())
            return;

        List <Board> possibleBoards = this.board.generateAllBoardFutureStatesOneDiskMoved();
        for (Board board : possibleBoards) {
            if (!this.graph.hasNode(board.toAlphacode())) {
                this.graph.createdNodes.add(board.toAlphacode());
                this.childrens.add(new Node(board.toAlphacode(), this, board, this.graph));
            } else {
                for (Node posibleBrother : fifo) {
                    if (posibleBrother.aphacode.equals(board.toAlphacode())) {
                        this.brothers.add(posibleBrother);
                        posibleBrother.brothers.add(this);
                    }
                }
            }
        }
        this.generateCosts();
    }

    private void generateCosts() {
        Random rand = new Random();
        if (this.father != null) {
            Integer fathersCost = this.father.cost.get(this);
            this.cost.put(this.father, fathersCost);
        }

        if (this.brothers.size() > 0) {
            for (Node bro : this.brothers) {
                if (bro.cost.containsKey(this)) {
                    this.cost.put(bro, bro.cost.get(this));
                } else {
                    this.cost.put(bro, rand.nextInt(100) + 1);
                }
            }
        }

        if (this.childrens.size() > 0) {
            for (Node child : this.childrens) {
                this.cost.put(child, rand.nextInt(100) + 1);
            }
        }
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

        return childrens;
    }

    public List <Node> getbrothers() {
        return brothers;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return this.board.toAlphacode();
    }
    public String getAphacode(){return this.aphacode;}
}
