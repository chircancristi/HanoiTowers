import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

       Player player=new Player(3,3);
       System.out.println("NEVER SAY NEVER");
       player.DFS(player.getGraph().getRoot(),20);

    }
}
