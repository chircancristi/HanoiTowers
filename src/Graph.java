public class Graph {
    private Node root;

    Graph(int towersNo, int disksNo) throws Exception {
        this.root = generateRoot(towersNo, disksNo);
    }

    private Node generateRoot(int towersNo, int diskNo) throws Exception {
        StringBuilder rootAlhacode = new StringBuilder();
        for (int i = 0; i < diskNo; i++)
            rootAlhacode.append('a');

        Node node = new Node(rootAlhacode.toString(), null, new Board(towersNo,diskNo));

        return node;
    }
}
