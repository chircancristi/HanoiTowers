import java.util.ArrayList;
import java.util.List;

public class Node {
    private String aphacode;
    private Node father;
    private List<Node> childrens;
    private List<Node> brothers;
    private Board board;
    private Graph graph;

    public Node(String aphacode , Node father, Board board, Graph graph) {
        this.aphacode = aphacode;
        this.father = father;
        this.board = board;
        this.graph = graph;

        this.brothers = new ArrayList <>();
        this.childrens = new ArrayList <>();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node && ((Node) obj).aphacode.equals(this.aphacode);
    }

    public void generateChildren(List<Node> fifo) {
        if (this.isFinal())
            return;

        List<Board> possibleBoards = this.board.generateAllBoardFutureStatesOneDiskMoved();
        for (Board board : possibleBoards) {
            if (!this.graph.hasNode(board.toAlphacode())) {
                this.graph.createdNodes.add(board.toAlphacode());
                this.childrens.add(new Node(board.toAlphacode(), this, board, this.graph));
            } else {
                for (Node posibleBrother: fifo){
                    if (posibleBrother.aphacode.equals(board.toAlphacode())){
                        this.brothers.add(posibleBrother);
                        posibleBrother.brothers.add(this);
                    }
                }
            }
        }
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
}
