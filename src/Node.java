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
    private Integer heuristic = 0;

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
        this.generateHeurestic();
    }

    private void generateHeurestic() {
        String alphaCode = this.aphacode;
        Map <Character, Integer> mapDisks = new HashMap <>();
        Map <Character, Integer> mapNumberofDisks = new HashMap <>();

        int auxValue = 0;
        for (int i = 0; i < alphaCode.length(); i++) {
            mapNumberofDisks.put(alphaCode.charAt(i), 0);

        }
        if (alphaCode.charAt(alphaCode.length() - 1) != 'a') {
            boolean ok = true;
            int lastTowerDiskOverFlow = 0;
            for (int i = alphaCode.length() - 2; i >= 0; i--) {
                if (alphaCode.charAt(i) != alphaCode.charAt(alphaCode.length() - 1)) {
                    ok = false;
                    if (mapNumberofDisks.get(alphaCode.charAt(i)) != 0)
                        auxValue = mapDisks.get(alphaCode.charAt(i)) + i + 1;
                    else auxValue = i + 1;
                    mapDisks.put(alphaCode.charAt(i), auxValue);
                    auxValue = mapNumberofDisks.get(alphaCode.charAt(i)) + 1;
                    mapNumberofDisks.put(alphaCode.charAt(i), auxValue);
                } else {
                    if (ok == false) {
                        lastTowerDiskOverFlow = lastTowerDiskOverFlow + (alphaCode.length() - (i + 1)) * 5;
                    }
                }
            }
            this.heuristic = lastTowerDiskOverFlow;
            for (char tower : mapDisks.keySet()) {
                if (mapNumberofDisks.get(tower) >= 2)
                    this.heuristic = this.heuristic + (mapDisks.get(tower) + alphaCode.length() - mapNumberofDisks.get(tower)) * 5;
                else this.heuristic = this.heuristic + mapDisks.get(tower) * 5;
            }


        } else {
            this.heuristic = alphaCode.length() * 10;
            for (int i = 0; i < alphaCode.length() - 1; i++) {
                if (mapNumberofDisks.get(alphaCode.charAt(i)) != 0)
                    auxValue = mapDisks.get(alphaCode.charAt(i)) + i + 1;
                else auxValue = i + 1;
                mapDisks.put(alphaCode.charAt(i), auxValue);
                auxValue = mapNumberofDisks.get(alphaCode.charAt(i)) + 1;
                mapNumberofDisks.put(alphaCode.charAt(i), auxValue);
            }
            for (char tower : mapDisks.keySet()) {
                if (tower == 'a') {
                    this.heuristic = this.heuristic + mapDisks.get(tower) * 5 + mapNumberofDisks.get(tower);
                } else {
                    if (mapNumberofDisks.get(tower) >= 2) {
                        this.heuristic = this.heuristic + ((mapDisks.get(tower)) * 5 - mapNumberofDisks.get(tower));
                    } else {
                        this.heuristic = this.heuristic + (mapDisks.get(tower)) * 5;
                    }
                }
            }

        }

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
                    this.cost.put(bro, rand.nextInt(20000) + 1);
                }
            }
        }

        if (this.childrens.size() > 0) {
            for (Node child : this.childrens) {
                this.cost.put(child, rand.nextInt(20000) + 1);
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

    public int getHeuristic() {
        return heuristic;
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

    public String getAphacode() {
        return this.aphacode;
    }
}
