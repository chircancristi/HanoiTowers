import java.sql.Time;

public class Result {
    public int solutionLength;
    public int noOfParsedStates;
    public long timeStarted;
    public long timeEnded;
    public float timeNeeded;


    public Result() {
        this.solutionLength = 0;
        this.noOfParsedStates = 0;
        this.timeStarted = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.timeEnded = System.currentTimeMillis();
        System.out.println("A inceput la "+ this.timeStarted + "s-a terminat la " + this.timeEnded);
        this.timeNeeded = this.timeEnded - this.timeStarted;
        System.out.println("Calcul final este "+this.timeNeeded);
    }


}
