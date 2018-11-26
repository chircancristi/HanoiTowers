import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board {
    public List <Tower> towers = new ArrayList <>();
    public List <Disk> disks = new ArrayList <>();
    public int towerNumber;
    public int diskNumber;
    public Disk lastMovedDisk = null;


    Board(Board board) {

        for (Tower tower : board.towers) {
            this.towers.add(new Tower(tower));
        }

        for (Disk disk : board.disks) {
            this.disks.add(new Disk(disk));
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
            this.disks.add(disk);
        }
        this.disks.sort(Comparator.comparing(Disk::getSize));
    }

    public boolean canItBeMoved(Disk disk) {
        for (Tower tower : this.towers) {
            if (!tower.equals(disk.tower) && tower.canReceiveDisk(disk)) {
                return true;
            }
        }
        return false;
    }

    public String toAlphacode() {
        StringBuilder alphacode = new StringBuilder();

        for (int i = 0; i < this.diskNumber; i++) {
            Tower tower = this.getTowerByDiskSize(i);

            if (tower != null)
                alphacode.append(tower.index + 'a');
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
        return null;
    }
}
