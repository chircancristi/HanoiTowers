import java.sql.Time;

public class Result {
    public int solutionLength;
    public int noOfParsedStates;
    public long timeStarted;
    public long timeEnded;
    public long timeNeeded;


    public Result() {
        this.solutionLength = 0;
        this.noOfParsedStates = 0;
        this.timeStarted = System.nanoTime();
    }

    public void stopTimer() {
        this.timeEnded = System.nanoTime();
        this.timeNeeded = this.timeEnded - this.timeStarted;

    }


}
