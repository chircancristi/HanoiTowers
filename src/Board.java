import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable{
    public List <Tower> towers = new ArrayList <>();
    public int towerNumber;
    public int diskNumber;
    public Disk lastMovedDisk = null;


    Board(Board board) {

        for (Tower tower : board.towers) {
            this.towers.add(new Tower(tower));
        }

        this.towerNumber = board.towerNumber;
        this.diskNumber = board.diskNumber;
        this.lastMovedDisk = board.lastMovedDisk;
    }

    Board(int towerNumber, int diskNumber) throws Exception {
        if (towerNumber <= 2 && diskNumber > 1) {
            throw new Exception("Unsolvable");
        }
        this.towerNumber = towerNumber;
        this.diskNumber = diskNumber;

        int i;
        for (i = 0; i < towerNumber; i++) {
            this.towers.add(new Tower(i));
        }
        for (i = 0; i < diskNumber; i++) {
            Disk disk = new Disk(diskNumber - i - 1, this.towers.get(0));
            this.towers.get(0).disks.push(disk);
        }
    }

    public boolean canItBeMoved(Disk disk) {
        for (Tower tower : this.towers) {
            if (!tower.equals(disk.tower) && tower.canReceiveDisk(disk)) {
                return true;
            }
        }
        return false;
    }

    public List <Tower> whereCanDiskBeMoved(Disk disk) {
        if (disk == null){
            return null;
        }
        List<Tower> possibleTowers = new ArrayList <>();
        for (Tower tower : this.towers) {
            if (!tower.equals(disk.tower) && tower.canReceiveDisk(disk)) {
                possibleTowers.add(tower);
            }
        }
        return possibleTowers;
    }

    public String toAlphacode() {
        StringBuilder alphacode = new StringBuilder();

        for (int i = 0; i < this.diskNumber; i++) {
            Tower tower = this.getTowerByDiskSize(i);

            if (tower != null)
                alphacode.append((char)(tower.index + 97));
        }

        return alphacode.toString();
    }

    private Tower getTowerByDiskSize(int size){
        for (Tower tower : this.towers){
            for (Disk disk : tower.disks) {
                if (disk.size == size)
                    return tower;
            }
        }
        return null;
    }

    public List<Board> generateAllBoardFutureStatesOneDiskMoved() {
        List<Board> possibleBoards = new ArrayList <>();
        List<Tower> possibleTowers;
        Disk pieceThatWasMoved;
        int currentTowerIndex;
        int toMoveTowerIndex;

        for ( Tower tower : this.towers) {

            if (tower.disks.size() == 0)
                continue;

           /* if (tower.disks.peek().equals(this.lastMovedDisk))
                continue;*/

            possibleTowers = whereCanDiskBeMoved(tower.disks.peek());

            if (possibleTowers != null){
                currentTowerIndex = tower.index;
                for (Tower toMoveTower : possibleTowers) {
                    toMoveTowerIndex = toMoveTower.index;

                    possibleBoards.add(new Board(this));
                    Board newBoard = possibleBoards.get(possibleBoards.size() - 1);
                    pieceThatWasMoved = newBoard.towers.get(currentTowerIndex).disks.pop();
                    newBoard.towers.get(toMoveTowerIndex).disks.push(pieceThatWasMoved);
                    newBoard.lastMovedDisk = pieceThatWasMoved;
                    //System.out.println(newBoard.toAlphacode());
                }

            }
        }

        return possibleBoards;
    }
}
