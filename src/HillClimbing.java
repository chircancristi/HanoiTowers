import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HillClimbing {
    private Result result;
    private List<Node> passedNodes;
    private Set<Node> settled;

    HillClimbing() {
        passedNodes = new ArrayList<>();
        settled = new HashSet<>();
        result = new Result();
    }

    public void hillClimbingSearch(Node root) {
        Node currentNode = root;
        Node nextNode = null;
        int noOfParsedStates = 1;
        int solutionLength = 1;


        passedNodes.add(root);
        while (true) {
            int nextVal = -1;
            if (currentNode.isFinal()) {
                break;
            }
            for (Node childen : currentNode.getChildren()) {
                if (!settled.contains(childen)) {
                    int nodeFValue = childen.getHeuristic() + currentNode.cost.get(childen);
                    if (nextVal == -1) {
                        nextVal = nodeFValue;
                        nextNode=childen;
                    } else {
                        if (nextVal > nodeFValue) {
                            nextVal = nodeFValue;
                            nextNode = childen;
                        }
                    }
                }
            }
            for (Node brother : currentNode.getbrothers()) {
                if (!settled.contains(brother)) {
                    int nodeFValue = brother.getHeuristic() + currentNode.cost.get(brother);
                    if (nextVal == -1) {
                        nextVal = nodeFValue;
                        nextNode=brother;
                    } else {
                        if (nextVal > nodeFValue) {
                            nextVal = nodeFValue;
                            nextNode = brother;
                        }
                    }
                }
            }
            if (nextVal == -1) {
                int i;
                Boolean hasRoutes=false;
                while (hasRoutes==false) {
                    i=passedNodes.size()-1;
                    for (Node children : passedNodes.get(i).getChildren()) {
                        if (!settled.contains(children)) {
                            hasRoutes = true;
                            break;
                        }
                    }
                    for (Node brother : passedNodes.get(i).getbrothers()) {
                        if (!settled.contains(brother)) {
                            hasRoutes = true;
                            break;
                        }
                    }
                    if (hasRoutes==false){
                        passedNodes.remove(i);
                        solutionLength = solutionLength - 1;
                    }
                }
                settled.add(currentNode);
                currentNode = passedNodes.get(passedNodes.size() - 1);

            } else {
                settled.add(currentNode);
                passedNodes.add(nextNode);
                currentNode = nextNode;
                solutionLength += 1;
            }
            noOfParsedStates += 1;
        }
        this.result.noOfParsedStates=noOfParsedStates;
        this.result.solutionLength=solutionLength;
        this.result.stopTimer();
    }

    public Result getResult() {
        return result;
    }
}
