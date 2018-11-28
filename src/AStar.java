import java.util.*;

public class AStar {
    public Result result;

    public AStar(Node root) {
        this.result = new Result();

        List <Node> closedSet = new ArrayList <>();
        List <Node> openSet = new ArrayList <>();
        Map <Node, Node> cameFrom = new HashMap <>();
        Map <Node, Integer> gScore = new HashMap <>();
        Map <Node, Integer> fScore = new HashMap <>();

        openSet.add(root);
        gScore.put(root, 0);
        fScore.put(root, root.getHeuristic());

        while (!openSet.isEmpty()) {
            Node current = this.getCurrentNode(openSet, fScore);

            if (current.isFinal()) {
                result.stopTimer();

                List <Node> total_path = getSolution(current, cameFrom);

                this.result.solutionLength = total_path.size();
                return;

            }

            openSet.remove(current);
            closedSet.add(current);

            List <Node> neighborsOfCurrent = getAllNeighbors(current);


            for (Node neighbor : neighborsOfCurrent) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                this.result.noOfParsedStates += 1;

                int tentative_gScore = gScore.get(current) + current.cost.get(neighbor);

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentative_gScore >= gScore.get(neighbor)) {
                    continue;
                }

                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentative_gScore);
                fScore.put(neighbor, gScore.get(neighbor) + neighbor.getHeuristic());
            }
        }
    }

    private List <Node> getAllNeighbors(Node current) {
        List <Node> neighborsOfCurrent = new ArrayList <>();

        neighborsOfCurrent.addAll(current.getChildren());
        neighborsOfCurrent.addAll(current.getbrothers());
        if (current.getFather() != null) {
            neighborsOfCurrent.add(current.getFather());
        }

        return neighborsOfCurrent;
    }

    private List <Node> getSolution(Node current, Map <Node, Node> cameFrom) {
        Stack <Node> stack = new Stack <>();
        List <Node> total_path = new ArrayList <>();

        stack.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            stack.add(current);
        }

        while (!stack.isEmpty()) {
            total_path.add(stack.pop());
        }

        return total_path;
    }

    private Node getCurrentNode(List <Node> openSet, Map <Node, Integer> fScore) {
        int min = Integer.MAX_VALUE;
        Node current = null;
        for (Node node : openSet) {
            if (fScore.containsKey(node)) {
                if (fScore.get(node) < min) {
                    current = node;
                }
            }
        }
        return current;
    }
}
