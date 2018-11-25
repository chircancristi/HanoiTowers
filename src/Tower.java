import java.util.Stack;

public class Tower {
    public int index;
    public Stack <Disk> disks;

    Tower(Tower tower) {
        this.index = tower.index;
        disks = new Stack <>();
        for (Disk disk : tower.disks) {
            this.disks.add(new Disk(disk));
        }
    }

    Tower(int index) {
        this.index = index;
        disks = new Stack <>();
    }

    public Boolean checkTower(int totalDisks) {
        return disks.size() == totalDisks;
    }

    public boolean canReceiveDisk(Disk disk) {
        return this.disks.size() == 0 || this.disks.peek().size > disk.size;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tower && ((Tower) obj).index == this.index;
    }
}
