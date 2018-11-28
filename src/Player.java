import java.util.*;

class Player {
    private List <Board> boards = new ArrayList <>();
    private Graph graph;

    Player(int towers, int disks) throws Exception {
        boards.add(new Board(towers, disks));
        this.graph = new Graph(towers, disks);
    }

    private boolean checkBoards(Board checkedBoard) {

        for (Board board : this.boards) {
            boolean check = true;
            for (int i = 0; i < board.towers.size(); i++) {
                if (checkedBoard.towers.get(i).disks.size() != board.towers.get(i).disks.size()) {
                    check = false;
                } else {
                    for (int j = 0; j < board.towers.get(i).disks.size(); j++) {
                        if (board.towers.get(i).disks.get(j).size != checkedBoard.towers.get(i).disks.get(j).size) {
                            check = false;
                        }
                    }
                }
            }
            if (!check) return true;
        }
        return false;
    }

    private boolean checkIfDone(Board board) {
        boolean check = false;
        for (Tower tower : board.towers) {
            if (tower.checkTower(board.diskNumber)) {
                if (tower.index == 0) board.lastMovedDisk = null;
                else check = true;
            }
        }
        return check;
    }

    public Result uniformCost() {
        UniformCost uniformCost=new UniformCost(this.graph.createdNodes.size(),this.graph);
        uniformCost.uniformCostSearch();
        uniformCost.result.solutionLength=uniformCost.printPath();
        return uniformCost.result;
    }



    public Result IDS() {
       Ids ids=new Ids();
       ids.IDS(this.graph.getRoot());
       return ids.result;
    }

    public Result hillClimbing() {
        HillClimbing hillClimbing=new HillClimbing();
        hillClimbing.hillClimbingSearch(this.graph.getRoot());
        return hillClimbing.getResult();
    }

    public Result aStar() {
        AStar alg = new AStar(this.graph.getRoot());

        return alg.result;
    }

    public Result randomOptimized() throws Exception {
        Result result=new Result();
        int steps = 0;
        while (true) {

            Random rand = new Random();
            Board checkBoard = new Board(this.boards.get(this.boards.size() - 1));

            int randomTowerIndex = rand.nextInt(checkBoard.towerNumber);
            Tower randomTower = checkBoard.towers.get(randomTowerIndex);

            while (randomTower.disks.size() == 0 || randomTower.disks.peek().equals(checkBoard.lastMovedDisk) || !checkBoard.canItBeMoved(randomTower.disks.peek())) {
                result.noOfParsedStates=result.noOfParsedStates+1;
                randomTowerIndex = rand.nextInt(checkBoard.towerNumber);
                randomTower = checkBoard.towers.get(randomTowerIndex);
            }

            int randomToMoveTowerIndex = rand.nextInt(checkBoard.towerNumber);
            Tower randomToMoveTower = checkBoard.towers.get(randomToMoveTowerIndex);

            while (randomToMoveTowerIndex == randomTowerIndex || !randomToMoveTower.canReceiveDisk(randomTower.disks.peek())) {
                result.noOfParsedStates=result.noOfParsedStates+1;
                randomToMoveTowerIndex = rand.nextInt(checkBoard.towerNumber);
                randomToMoveTower = checkBoard.towers.get(randomToMoveTowerIndex);
            }

            randomToMoveTower.disks.push(randomTower.disks.pop());

            if (!this.checkBoards(checkBoard)) {
                randomTower.disks.push(randomToMoveTower.disks.pop());
            } else {
                result.solutionLength+=1;
                steps++;
                checkBoard.lastMovedDisk = randomToMoveTower.disks.peek();
                checkBoard.lastMovedDisk.tower = randomToMoveTower;

                Boolean check = this.checkIfDone(checkBoard);

                if (check) {
                    break;
                }
                if (result.solutionLength==10000){
                    result.solutionLength=-1;
                }
                this.boards.add(checkBoard);
            }
        }
        result.stopTimer();
        return result;
    }

    public Graph getGraph() {
        return graph;
    }
}


