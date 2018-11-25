public class Disk {
    public int size;
    public Tower tower;


    Disk(Disk disk) {
        this.size = disk.size;
    }

    Disk(int size, Tower tower) {
        this.size = size;
        this.tower = tower;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Disk && ((Disk) obj).size == this.size;
    }
}
