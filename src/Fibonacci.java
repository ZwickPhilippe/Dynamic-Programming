import exceptions.FibonacciException;

public class Fibonacci {

    public static long solveIterative(int number){
        if(number>92) throw new FibonacciException("Result of: " + number + " is too big for Java.lang type (max: 92)");
        if(number<=0) throw new FibonacciException("Input must be greater than 0");

        long[] helpArray= new long[number];
        helpArray[0]=1;
        helpArray[1]=1;
        for (int i = 2; i < number; i++) {
            helpArray[i]=helpArray[i-1]+helpArray[i-2];
        }
        return helpArray[number-1];
    }

    public static long solveRecursive(int number){
        long[] helpArray= new long[number];
        return recursiveSolver(number-1,helpArray);

    }

    private static long recursiveSolver(int number, long[] helpArray){
        if(helpArray[number]==0){
            if(number==0||number==1){
                helpArray[number]=1;
            }else{
                helpArray[number]=recursiveSolver(number-1,helpArray)+recursiveSolver(number-2,helpArray);
            }
        }
        return helpArray[number];

    }

    /**
     * method to test the speed of each approach
     */
    public static void stopTime(int number){
        System.out.println("Stopwatch:");
        long starttime=System.nanoTime();
        long result=solveIterative(number);
        System.out.println("Iterative approach returned: " + result + " and took " + (System.nanoTime()-starttime)+"ns");
        starttime=System.nanoTime();
        result=solveRecursive(number);
        System.out.println("Recursive approach returned: " + result + " and took " + (System.nanoTime()-starttime)+"ns");

    }
}
