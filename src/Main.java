public class Main {

    public static void main(String[] args) throws Exception {

       Player player=new Player(3,3);

       Result resultHc=player.hillClimbing();
       Result resultIds=player.IDS();
       Result resultUC=player.uniformCost();

       System.out.println(resultHc.solutionLength + " " +resultHc.noOfParsedStates);
       System.out.println(resultIds.solutionLength+ " "+ resultIds.noOfParsedStates);
       System.out.println(resultUC.solutionLength+ "  "+ resultUC.noOfParsedStates);

    }
}
