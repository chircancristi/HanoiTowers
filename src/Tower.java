import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.List;
import java.util.Stack;

public class Tower {
    public int index;
    public Stack<Disk> disks ;

    Tower(Tower tower){
        this.index=tower.index;
        disks=new Stack<>();
        for (Disk disk : tower.disks) {
            this.disks.add(new Disk(disk));
        }
    }
    Tower(int index){
        this.index=index;
        disks=new Stack<>();
    }
    public Boolean checkTower(int totalDisks) {
        if (disks.size()==totalDisks ) {
            return true;
        }
        return false;
    }
    public boolean canReceiveDisk(Disk disk){
        if (this.disks.size()==0 || this.disks.peek().size>disk.size)
            return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tower && ((Tower) obj).index==this.index)
            return true;
        return false;
    }
}
