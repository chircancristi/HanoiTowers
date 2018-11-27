import java.util.*;

public class UniformCost {
    private List<Node> priorityQueue;
    private Set<Node> settled;
    private Map <String,Integer> distances;
    private int numberOfNodes;
    private int adjacencyMatrix[][];
    private LinkedList<Node> path;
    private Map<Node,Node> parent;
    private Node finalNode;
    Graph graph;

    public UniformCost(int numberOfNodes,Graph graph)
    {
        this.numberOfNodes = numberOfNodes;
        this.settled = new HashSet<>();
        this.priorityQueue = new ArrayList<>();
        this.distances = new HashMap<>();
        this.path = new LinkedList<>();
        this.parent = new HashMap<>();
        this.graph=graph;
    }
    public int uniformCostSearch(){
        Node evaluationNode;
        for (int i = 1; i < numberOfNodes; i++)
        {
            distances.put(graph.createdNodes.get(i),999999999);
        }

        priorityQueue.add(graph.getRoot());
        distances.put(graph.getRoot().getAphacode(),0);
        while (!priorityQueue.isEmpty()){
            evaluationNode=priorityQueue.get(0);
            for (int i=0;i<priorityQueue.size();i++)
                if (priorityQueue.get(i).getAphacode().compareTo(evaluationNode.getAphacode())==0)
                    priorityQueue.remove(i);
            if (evaluationNode.isFinal()){
                this.finalNode=evaluationNode;
                return distances.get(evaluationNode.getAphacode());
            }
            settled.add(evaluationNode);
            addChildenToQueue(evaluationNode);
        }
        return 0;
    }
    public void addChildenToQueue(Node parent){
        for (Node childen :parent.getChildren()){
            if (!settled.contains(childen)) {
                if (distances.get(childen.getAphacode())>parent.cost.get(childen)+distances.get(parent.getAphacode())) {
                    distances.put(childen.getAphacode(),parent.cost.get(childen)+distances.get(parent.getAphacode()));
                    this.parent.put(childen,parent);
                }
            }
            if (priorityQueue.contains(childen)){
                priorityQueue.remove(childen);
            }
            priorityQueue.add(childen);
        }
        for (Node brother :parent.getbrothers()){
            if (!settled.contains(brother)) {
                if (distances.get(brother.getAphacode())>parent.cost.get(brother)+distances.get(parent.getAphacode())) {
                    distances.put(brother.getAphacode(),parent.cost.get(brother)+distances.get(parent.getAphacode()));
                    this.parent.put(brother,parent);
                }
                if (priorityQueue.contains(brother)){
                    priorityQueue.remove(brother);
                }
                priorityQueue.add(brother);
            }

        }
        this.sortPriority();
    }

    private void sortPriority() {
        int i,j;
        for (i=0;i<this.priorityQueue.size()-1;i++)
            for (j=i+1;j<this.priorityQueue.size();j++){
            if (distances.get(this.priorityQueue.get(i).getAphacode())>distances.get(this.priorityQueue.get(j).getAphacode())){
                Node test=this.priorityQueue.get(i);
                Node test2=this.priorityQueue.get(j);
                this.priorityQueue.set(i,test2);
                this.priorityQueue.set(j,test);
            }
            if (distances.get(this.priorityQueue.get(i).getAphacode())==distances.get(this.priorityQueue.get(j).getAphacode())
                && this.priorityQueue.get(i).getAphacode().compareTo(this.priorityQueue.get(j).getAphacode())==-1){
                Node test=this.priorityQueue.get(i);
                Node test2=this.priorityQueue.get(j);
                this.priorityQueue.set(i,test2);
                this.priorityQueue.set(j,test);
            }
        }
    }
    public void printPath(){
        path.add(this.finalNode);
        boolean found = false;
        Node vertex=this.finalNode;
        while (!found)
        {
            if (vertex.getAphacode().compareTo(this.graph.getRoot().getAphacode())==0)
            {
                found = true;
                continue;
            }
            path.add(parent.get(vertex));
            vertex = parent.get(vertex);
        }

        System.out.println("The Path is ");
        Iterator<Node> iterator = path.descendingIterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }
}
