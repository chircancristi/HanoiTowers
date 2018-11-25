import java.util.ArrayList;
import java.util.List;

public class Graph {
    private Node root;
    public List<String> createdNodes;
    public List<Node> nodesFifo;

    Graph(int towersNo, int disksNo) throws Exception {
        this.createdNodes = new ArrayList <>();
        this.nodesFifo = new ArrayList <>();

        this.root = generateRoot(towersNo, disksNo);

        this.nodesFifo.add(this.root);

        while (this.nodesFifo.size() > 0){
            System.out.println(this.nodesFifo);
            this.nodesFifo.get(0).generateChildren(this.nodesFifo);

            if (this.nodesFifo.get(0).getChildren().size() == 0 ) {
                this.nodesFifo.remove(0);
                continue;
            }

            for (Node node : this.nodesFifo.get(0).getChildren()) {
                this.nodesFifo.add(node);
            }
            this.nodesFifo.remove(0);
        }
    }

    private Node generateRoot(int towersNo, int diskNo) throws Exception {
        StringBuilder rootAlhacode = new StringBuilder();
        for (int i = 0; i < diskNo; i++)
            rootAlhacode.append('a');

        this.createdNodes.add(rootAlhacode.toString());
        Node node = new Node(rootAlhacode.toString(), null, new Board(towersNo,diskNo), this);

        return node;
    }

    public boolean hasNode(String s) {
        if (this.createdNodes.contains(s))
            return true;

        return false;
    }

}
