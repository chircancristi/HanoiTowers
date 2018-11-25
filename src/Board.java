import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    public List<Tower> towers = new ArrayList<>();
    public int towerNumber;
    public int diskNumber;
    public Disk lastMovedDisk = null;


    public Board(Board board){

        for (Tower tower : board.towers){
            this.towers.add(new Tower(tower));
        }
        this.towerNumber=board.towerNumber;
        this.diskNumber=board.diskNumber;
        this.lastMovedDisk=board.lastMovedDisk;
    }
    Board(int towerNumber, int diskNumber) throws Exception {
        if (towerNumber<=2 && diskNumber>1 ) {
            throw new Exception("Unsolvable");
        }
        this.towerNumber = towerNumber;
        this.diskNumber = diskNumber;

        int i;
        for (i = 0; i < towerNumber; i++) {
            this.towers.add(new Tower(i));
        }
        for (i = 0; i < diskNumber; i++) {
            this.towers.get(0).disks.push(new Disk(diskNumber-i-1, this.towers.get(0)));
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


}
