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
        this.timeStarted = System.currentTimeMillis();
    }

    public void stopTimer(){
        this.timeEnded = System.currentTimeMillis();

        this.timeNeeded = this.timeEnded - this.timeStarted;
    }

    public String getDurationString(){
        StringBuilder time = new StringBuilder("It took ");

        Time timeInHMS = new Time(this.timeNeeded);

        if (timeInHMS.getHours() != 0)
            time.append(timeInHMS.getHours() + " hours");

        if (timeInHMS.getMinutes() != 0)
            time.append(timeInHMS.getMinutes() + " minutes");

        if (timeInHMS.getSeconds() != 0)
            time.append(timeInHMS.getSeconds() + " seconds");

        return time.toString();
    }
}
