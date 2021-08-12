import java.util.Arrays;

public class ConstrainedLongestCommonSubsequence {

    private final char[] firstWord; //String 1
    private final char[] secondWord; //String 2
    private final char[] pattern; //Pattern that needs to be included in both Strings
    private int[][][] table;

    /**
     *
     * @param firstWord - is the first String in which the LCS is searched
     * @param secondWord - is the second String in which the LCS is searched
     * @param pattern - pattern that MUST be included in both substrings (=constraint).
     *                If no pattern is given, the CLCS problem turns into the LCS problem
     */

    public ConstrainedLongestCommonSubsequence(String firstWord, String secondWord, String pattern){
        this.firstWord=firstWord.toCharArray();
        this.secondWord=secondWord.toCharArray();
        this.pattern=pattern.toCharArray();
        createTable();
    }

    /**
     * This constructor is used if there is no constraint (no pattern that needs to be included in the Subsequence)
     * The problem is therefore minimized to the LCS problem
     * @param firstWord - is compared to the second word
     * @param secondWord - is compared to the first word
     */
    public ConstrainedLongestCommonSubsequence(String firstWord, String secondWord){
        this(firstWord,secondWord,"");
    }

    private void createTable(){
        table=new int[pattern.length+1][firstWord.length+1][secondWord.length+1];
        for (int k = 0; k < pattern.length+1 ; k++) {
            for (int i = 0; i < firstWord.length+1 ; i++) {
                for (int j = 0; j < secondWord.length+1 ; j++) {
                    if (i == 0 || j == 0) {
                        if(k!=0){ //for k=0 we need Zeros
                            table[k][i][j]=Integer.MIN_VALUE;
                        }
                    } else if (k>0 && (firstWord[i - 1] == secondWord[j - 1] && secondWord[j - 1] == pattern[k - 1])) { //if all are equal, we take value from one layer lower
                        int valueBelow = table[k-1][i-1][j-1]+1;
                        table[k][i][j]=valueBelow;
                    }else if(firstWord[i - 1] == secondWord[j - 1]){ //if string (but not pattern) are equal, we add 1 to value from the upper left cell
                        table[k][i][j]=1+table[k][i-1][j-1];
                    } else { //if nothing is equal, we take the maximum from above, and left to current cell
                        int maxValue = Math.max(table[k][i-1][j], table[k][i][j-1]);
                        table[k][i][j]=maxValue;
                    }
                }
            }

        }
    }

    public String solveCLCS(){
        int k= pattern.length;
        int i= firstWord.length;
        int j=secondWord.length;
        int tableEntry=table[k][i][j];
        char[] returnValue= new char[tableEntry];

        while(tableEntry!=0){
            while (table[k][i][j] == table[k][i-1][j]) { //move up as long as value is equal
                i = i - 1;
            }
            while (table[k][i][j] == table[k][i][j-1]) { //move left as long as value is equal
                j = j - 1;
            }
            returnValue[tableEntry - 1] = firstWord[i - 1];
            if(table[k][i][j]!=table[k][i-1][j-1]+1){  //if value in upper left cell is smaller than one, we need to move one layer up (k-1)
                k=k-1;
            }
            i=i-1;
            j=j-1;
            tableEntry--;

        }
        return String.valueOf(returnValue);
    }

    public String solveLCS(){

        int i = firstWord.length ;
        int j = secondWord.length ;
        int tableEntry = table[0][i][j];
        char[] returnValue = new char[tableEntry];

        while (tableEntry != 0) {
            while (table[0][i][j] == table[0][i-1][j]) { //move up as long as value is equal
                i = i - 1;
            }
            while (table[0][i][j] == table[0][i][j-1]) { //move left as long as value is equal
                j = j - 1;
            }
            returnValue[tableEntry - 1] = firstWord[i - 1];
            i=i-1;
            j=j-1;
            tableEntry--;

        }
        return String.valueOf(returnValue);

    }




}
