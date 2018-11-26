import java.util.*;

public class UniformCost {
    private List<Node> priorityQueue;
    private Set<Node> settled;
    private Map <String,Integer> distances;
    private int numberOfNodes;
    private int adjacencyMatrix[][];
    private LinkedList<Integer> path;
    private int[] parent;
    Graph graph;

    public UniformCost(int numberOfNodes,Graph graph)
    {
        this.numberOfNodes = numberOfNodes;
        this.settled = new HashSet<Node>();
        this.priorityQueue = new ArrayList<Node>();
        this.distances = new HashMap<>();
        this.path = new LinkedList<Integer>();
        this.parent = new int[numberOfNodes + 1];
        this.graph=graph;
    }
    public int uniformCostSearch( Node root){
        Node evaluationNode;
        for (int i = 1; i <= numberOfNodes; i++)
        {
            distances.put(graph.createdNodes.get(i),999999999);
        }
        priorityQueue.add(root);
        distances.put(root.getAphacode(),0);
        while (!priorityQueue.isEmpty()){
            evaluationNode=priorityQueue.get(0);
            priorityQueue.remove(0);
            if (evaluationNode.isFinal()){
                return distances.get(evaluationNode.getAphacode());
            }
            settled.add(evaluationNode);
        }
        return 0;
    }

}
