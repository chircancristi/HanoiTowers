import java.util.List;

public class Disk {
    public int size;
    public Tower tower;


    Disk(Disk disk){
        this.size=disk.size;
    }
    Disk (int size,Tower tower){
        this.size=size;
        this.tower=tower;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Disk && ((Disk) obj).size==this.size)
             return true;
        return false;
    }
}
