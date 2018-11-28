public class Ids {
    Result result;
    Ids(){
        result=new Result();
    }
    public  boolean DFS (Node currentNode, int depth)
    {

        if (currentNode.isFinal())
            return  true;
        if (depth==0)
            return false;
        for (Node child: currentNode.getChildren()){
            this.result.noOfParsedStates+=1;
            if (DFS(child,depth-1))
                return true;
        }
        return  false;
    }
    public void IDS(Node root) {

        int depth=1;
        while(true)
            {
                if (DFS(root,depth)){
                    this.result.solutionLength=depth;
                    break;
                }
                depth=depth+1;
            }
        this.result.stopTimer();
    }
}
