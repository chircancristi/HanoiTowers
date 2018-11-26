import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Serializable{
    private Node root;
    public List<String> createdNodes;
    public List<Node> nodesFifo;
    private String absPath = (new File("").getAbsolutePath());

    Graph(int towersNo, int disksNo) throws Exception {
        this.createdNodes = new ArrayList <>();
        this.nodesFifo = new ArrayList <>();

        if (this.graphPreviouslyGenerated(towersNo, disksNo)){
            this.loadGraph(towersNo, disksNo);
        } else {

            this.root = generateRoot(towersNo, disksNo);

            this.nodesFifo.add(this.root);

            while (this.nodesFifo.size() > 0) {
                System.out.println(this.nodesFifo);
                this.nodesFifo.get(0).generateChildren(this.nodesFifo);

                if (this.nodesFifo.get(0).getChildren().size() == 0) {
                    this.nodesFifo.remove(0);
                    continue;
                }

                for (Node node : this.nodesFifo.get(0).getChildren()) {
                    this.nodesFifo.add(node);
                }
                this.nodesFifo.remove(0);
            }

            try {
                this.storeGraph(towersNo, disksNo);
            } catch (java.lang.StackOverflowError e){
                String filePathString = this.absPath + "\\GeneratedGraphs\\" + towersNo + '_' + disksNo + ".txt";
                File file = new File(filePathString);
                file.deleteOnExit();
            }
        }
    }

    private void storeGraph(int towersNo, int disksNo) throws IOException {
        String filePathString = this.absPath + "\\GeneratedGraphs\\" + towersNo + '_' + disksNo + ".txt";

        FileOutputStream fos = new FileOutputStream(filePathString);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    private void loadGraph(int towersNo, int disksNo) throws IOException, ClassNotFoundException {
        String filePathString = this.absPath + "\\GeneratedGraphs\\" + towersNo + '_' + disksNo + ".txt";

        FileInputStream fin = new FileInputStream(filePathString);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Graph g = (Graph) ois.readObject();
        this.root = g.root;
        this.createdNodes = g.createdNodes;
        this.nodesFifo = g.nodesFifo;
        this.absPath = g.absPath;
        ois.close();
    }

    private boolean graphPreviouslyGenerated(int towersNo, int disksNo) {
        String filePathString = this.absPath + "\\GeneratedGraphs\\" + towersNo + '_' + disksNo + ".txt";
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
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

    public Node getRoot() {
        return this.root;
    }
}
