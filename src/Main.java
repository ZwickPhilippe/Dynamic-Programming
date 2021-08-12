public class Main {

    public static void main(String[] args) {


        KnapsackItem[] list = new KnapsackItem[5];
        list[0]=new KnapsackItem(1,1,"1");
        list[1]=new KnapsackItem(2,6,"2");
        list[2]=new KnapsackItem(5,18,"3");
        list[3]=new KnapsackItem(6,22,"4");
        list[4]=new KnapsackItem(7,28,"5");


        KnapsackProblem newProblem= new KnapsackProblem(11,list);


        newProblem.solveProblem();
        newProblem.visualizeSolution();

        ConstrainedLongestCommonSubsequence problemSolver = new ConstrainedLongestCommonSubsequence("ATCACA","ACTCAA","CC");
        System.out.println(problemSolver.solveCLCS());
        System.out.println(problemSolver.solveLCS());

    }
}
