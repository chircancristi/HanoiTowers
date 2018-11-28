import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        int towers, disks;
        int totalInstanceHc = 0;
        int totalInstanceIds = 0;
        int totalInstanceUc = 0;
        int totalInstanceRandom = 0;
        int totalInstanceAStar = 0;
        long totalTimeHc = 0;
        long totalTimeIds = 0;
        long totalTimeUc = 0;
        long totalTimeRandom = 0;
        long totalTimeAstar = 0;

        FileWriter fileWriter = new FileWriter("output.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (towers = 3; towers <= 5; towers++) {
            for (disks = 2; disks <= 5; disks++) {
                if (disks != 5 || towers != 5) {
                    System.out.println(towers + " turnuri " + disks + " discuri");
                    Player player = new Player(towers, disks);

                    Result resultHc = player.hillClimbing();
                    Result resultIds = player.IDS();
                    Result resultUC = player.uniformCost();
                    Result resultRandom = player.randomOptimized();
                    Result resultAStar = player.aStar();
                    printWriter.println("For " + towers + " towers and " + disks + " disks:");

                    printWriter.println("The solution length for Random Optimized is " + resultRandom.solutionLength + " number of checked states is " + resultRandom.noOfParsedStates + " the time is " + resultRandom.timeNeeded);
                    printWriter.println("The solution length for IDS is " + resultIds.solutionLength + " number of checked states is " + resultIds.noOfParsedStates + " the time is " + resultIds.timeNeeded);
                    printWriter.println("The solution length for Uniform Cost is " + resultUC.solutionLength + " number of checked states is " + resultUC.noOfParsedStates + " the time is " + resultUC.timeNeeded);
                    printWriter.println("The solution length for Hill Climbing is " + resultHc.solutionLength + " number of checked states is " + resultHc.noOfParsedStates + " the time is " + resultHc.timeNeeded);
                    printWriter.println("The solution length for A* is " + resultAStar.solutionLength + " number of checked states is " + resultAStar.noOfParsedStates + " the time is " + resultAStar.timeNeeded);

                    totalInstanceHc = resultHc.noOfParsedStates + totalInstanceHc;
                    totalInstanceAStar = resultAStar.noOfParsedStates + totalInstanceAStar;
                    totalInstanceIds = resultIds.noOfParsedStates + totalInstanceIds;
                    totalInstanceRandom = resultRandom.noOfParsedStates + totalInstanceRandom;
                    totalInstanceUc = resultUC.noOfParsedStates + totalInstanceUc;

                    totalTimeAstar = resultAStar.timeNeeded + totalTimeAstar;
                    totalTimeHc = resultHc.timeNeeded + totalTimeHc;
                    totalTimeIds = resultIds.timeNeeded + totalTimeIds;
                    totalTimeRandom = resultRandom.timeNeeded + totalTimeRandom;
                    totalTimeUc = resultUC.timeNeeded + totalTimeUc;
                }
            }
        }
        for (towers = 6; towers <= 11; towers++) {
            for (disks = 2; disks <= 3; disks++) {
                System.out.println(towers + " turnuri " + disks + " discuri");
                Player player = new Player(towers, disks);

                Result resultHc = player.hillClimbing();
                Result resultIds = player.IDS();
                Result resultUC = player.uniformCost();
                Result resultRandom = player.randomOptimized();
                Result resultAStar = player.aStar();
                printWriter.println("For " + towers + " towers and " + disks + " disks:");

                printWriter.println("The solution length for Random Optimized is " + resultRandom.solutionLength + " number of checked states is " + resultRandom.noOfParsedStates + "the time is " + resultRandom.timeNeeded);
                printWriter.println("The solution length for IDS is " + resultIds.solutionLength + " number of checked states is " + resultIds.noOfParsedStates + "the time is " + resultIds.timeNeeded);
                printWriter.println("The solution length for Uniform Cost is " + resultUC.solutionLength + " number of checked states is " + resultUC.noOfParsedStates + "the time is " + resultUC.timeNeeded);
                printWriter.println("The solution length for Hill Climbing is " + resultHc.solutionLength + " number of checked states is " + resultHc.noOfParsedStates + "the time is " + resultHc.timeNeeded);
                printWriter.println("The solution length for A* is " + resultAStar.solutionLength + " number of checked states is " + resultAStar.noOfParsedStates + "the time is " + resultAStar.timeNeeded);

                totalInstanceHc = resultHc.noOfParsedStates + totalInstanceHc;
                totalInstanceAStar = resultAStar.noOfParsedStates + totalInstanceAStar;
                totalInstanceIds = resultIds.noOfParsedStates + totalInstanceIds;
                totalInstanceRandom = resultRandom.noOfParsedStates + totalInstanceRandom;
                totalInstanceUc = resultUC.noOfParsedStates + totalInstanceUc;

                totalTimeAstar = resultAStar.timeNeeded + totalTimeAstar;
                totalTimeHc = resultHc.timeNeeded + totalTimeHc;
                totalTimeIds = resultIds.timeNeeded + totalTimeIds;
                totalTimeRandom = resultRandom.timeNeeded + totalTimeRandom;
                totalTimeUc = resultUC.timeNeeded + totalTimeUc;
            }
        }
        towers = 4;
        disks = 6;
        Player player = new Player(towers, disks);

        Result resultHc = player.hillClimbing();
        Result resultIds = player.IDS();
        Result resultUC = player.uniformCost();
        Result resultRandom = player.randomOptimized();
        Result resultAStar = player.aStar();
        printWriter.println("For " + towers + " towers and " + disks + " disks:");


        printWriter.println("The solution length for Random Optimized is " + resultRandom.solutionLength + " number of checked states is " + resultRandom.noOfParsedStates + "the time is " + resultRandom.timeNeeded);
        printWriter.println("The solution length for IDS is " + resultIds.solutionLength + " number of checked states is " + resultIds.noOfParsedStates + "the time is " + resultIds.timeNeeded);
        printWriter.println("The solution length for Uniform Cost is " + resultUC.solutionLength + " number of checked states is " + resultUC.noOfParsedStates + "the time is " + resultUC.timeNeeded);
        printWriter.println("The solution length for Hill Climbing is " + resultHc.solutionLength + " number of checked states is " + resultHc.noOfParsedStates + "the time is " + resultHc.timeNeeded);
        printWriter.println("The solution length for A* is " + resultAStar.solutionLength + " number of checked states is " + resultAStar.noOfParsedStates + "the time is " + resultAStar.timeNeeded);

        totalInstanceHc = resultHc.noOfParsedStates + totalInstanceHc;
        totalInstanceAStar = resultAStar.noOfParsedStates + totalInstanceAStar;
        totalInstanceIds = resultIds.noOfParsedStates + totalInstanceIds;
        totalInstanceRandom = resultRandom.noOfParsedStates + totalInstanceRandom;
        totalInstanceUc = resultUC.noOfParsedStates + totalInstanceUc;

        totalTimeAstar = resultAStar.timeNeeded + totalTimeAstar;
        totalTimeHc = resultHc.timeNeeded + totalTimeHc;
        totalTimeIds = resultIds.timeNeeded + totalTimeIds;
        totalTimeRandom = resultRandom.timeNeeded + totalTimeRandom;
        totalTimeUc = resultUC.timeNeeded + totalTimeUc;

        towers = 3;
        disks = 7;
        player = new Player(towers, disks);

        resultHc = player.hillClimbing();
        resultIds = player.IDS();
        resultUC = player.uniformCost();
        resultRandom = player.randomOptimized();
        resultAStar = player.aStar();
        printWriter.println("For " + towers + " towers and " + disks + " disks:");

        printWriter.println("The solution length for Random Optimized is " + resultRandom.solutionLength + " number of checked states is " + resultRandom.noOfParsedStates + "the time is " + resultRandom.timeNeeded);
        printWriter.println("The solution length for IDS is " + resultIds.solutionLength + " number of checked states is " + resultIds.noOfParsedStates + "the time is " + resultIds.timeNeeded);
        printWriter.println("The solution length for Uniform Cost is " + resultUC.solutionLength + " number of checked states is " + resultUC.noOfParsedStates + "the time is " + resultUC.timeNeeded);
        printWriter.println("The solution length for Hill Climbing is " + resultHc.solutionLength + " number of checked states is " + resultHc.noOfParsedStates + "the time is " + resultHc.timeNeeded);
        printWriter.println("The solution length for A* is " + resultAStar.solutionLength + " number of checked states is " + resultAStar.noOfParsedStates + "the time is " + resultAStar.timeNeeded);

        totalInstanceHc = resultHc.noOfParsedStates + totalInstanceHc;
        totalInstanceAStar = resultAStar.noOfParsedStates + totalInstanceAStar;
        totalInstanceIds = resultIds.noOfParsedStates + totalInstanceIds;
        totalInstanceRandom = resultRandom.noOfParsedStates + totalInstanceRandom;
        totalInstanceUc = resultUC.noOfParsedStates + totalInstanceUc;

        totalTimeAstar = resultAStar.timeNeeded + totalTimeAstar;
        totalTimeHc = resultHc.timeNeeded + totalTimeHc;
        totalTimeIds = resultIds.timeNeeded + totalTimeIds;
        totalTimeRandom = resultRandom.timeNeeded + totalTimeRandom;
        totalTimeUc = resultUC.timeNeeded + totalTimeUc;


        printWriter.println("The medium number of states tested for Random is " + totalInstanceRandom / 25 + " in a medium time of " + (float) totalTimeRandom / 25);
        printWriter.println("The medium number of states tested for IDS is " + totalInstanceIds / 25 + " in a medium time of " + (float) totalTimeIds / 25);
        printWriter.println("The medium number of states tested for Uniform Cost is " + totalInstanceUc / 25 + " in a medium time of " + (float) totalTimeUc / 25);
        printWriter.println("The medium number of states tested for HillClimbing is " + totalInstanceHc / 25 + " in a medium time of " + (float) totalTimeHc / 25);
        printWriter.println("The medium number of states tested for A* is " + totalInstanceAStar / 25 + " in a medium time of " + (float) totalTimeAstar / 25);

        printWriter.close();


    }

}
