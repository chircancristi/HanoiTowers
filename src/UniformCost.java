import java.util.*;

public class UniformCost {
    private List<Node> priorityQueue;
    private Set<Integer> settled;
    private int distances[];
    private int numberOfNodes;
    private int adjacencyMatrix[][];
    private LinkedList<Integer> path;
    private int[] parent;

    public UniformCost(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.settled = new HashSet<Integer>();
        this.priorityQueue = new ArrayList<Node>();
        this.distances = new int[numberOfNodes + 1];
        this.path = new LinkedList<Integer>();
        this.adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];
        this.parent = new int[numberOfNodes + 1];
    }
    public int uniformCostSearch( Node root){


        for (int i = 1; i <= numberOfNodes; i++)
        {
            distances[i] = 999999999;
        }
        return 0;
    }
}
