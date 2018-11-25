import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Player {
    private List <Board> boards = new ArrayList <>();

    Player(int towers, int disks) throws Exception {
        boards.add(new Board(towers, disks));
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
    private boolean checkIfDone(Board board){
        boolean check=false;
        for (Tower tower : board.towers) {
            if (tower.checkTower(board.diskNumber)) {
                if (tower.index == 0) board.lastMovedDisk = null;
                else check = true;
            }
    }
        return check;
    }
    public void uniformCost(){

    }

    public void IDS() {

    }

    public void hillClimbing() {

    }

    public void aStar() {

    }

    public void randomOptimized() throws Exception {
        int steps = 0;
        while (true) {
            Random rand = new Random();
            Board checkBoard = new Board(this.boards.get(this.boards.size() - 1));

            int randomTowerIndex = rand.nextInt(checkBoard.towerNumber);
            Tower randomTower = checkBoard.towers.get(randomTowerIndex);

            while (randomTower.disks.size() == 0 || randomTower.disks.peek().equals(checkBoard.lastMovedDisk) || !checkBoard.canItBeMoved(randomTower.disks.peek())) {
                randomTowerIndex = rand.nextInt(checkBoard.towerNumber);
                randomTower = checkBoard.towers.get(randomTowerIndex);
            }

            int randomToMoveTowerIndex = rand.nextInt(checkBoard.towerNumber);
            Tower randomToMoveTower = checkBoard.towers.get(randomToMoveTowerIndex);

            while (randomToMoveTowerIndex == randomTowerIndex || !randomToMoveTower.canReceiveDisk(randomTower.disks.peek())) {
                randomToMoveTowerIndex = rand.nextInt(checkBoard.towerNumber);
                randomToMoveTower = checkBoard.towers.get(randomToMoveTowerIndex);
            }

            randomToMoveTower.disks.push(randomTower.disks.pop());
            if (!this.checkBoards(checkBoard)) {
                randomTower.disks.push(randomToMoveTower.disks.pop());
            } else {
                steps++;
                checkBoard.lastMovedDisk = randomToMoveTower.disks.peek();
                checkBoard.lastMovedDisk.tower = randomToMoveTower;
                System.out.println("At step " + steps + " disk " + checkBoard.lastMovedDisk.size + " was moved from tower" + randomTower.index + " to tower" + randomToMoveTower.index);

                Boolean check = this.checkIfDone(checkBoard);

                if (check) {
                    break;
                }
                this.boards.add(checkBoard);
            }
        }
        System.out.println("Job done");
    }
}


