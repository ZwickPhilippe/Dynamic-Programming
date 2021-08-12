import java.util.Arrays;
import java.util.LinkedList;

public class KnapsackProblem {

    private final int capacity;
    private final KnapsackItem[] itemList;
    private int[][] table;

    public KnapsackProblem(int capacity, KnapsackItem[] itemsToInsert){
        this.capacity=capacity;
        this.itemList=itemsToInsert;
        createTable();
    }

    private void createTable(){
        this.table=new int[itemList.length+1][capacity+1];
        for (int i = 1; i <= itemList.length; i++) { 
            for (int g = 0; g <= capacity; g++) {
                KnapsackItem currentItemWeight=itemList[i-1];
                if(currentItemWeight.getWeight()>g){
                    table[i][g]=table[i-1][g];
                }else{
                    table[i][g]=Math.max(table[i-1][g],currentItemWeight.getValue()+table[i-1][g-currentItemWeight.getWeight()]);
                }
            }
        }

    }

    public LinkedList<KnapsackItem> solveProblem(){
        int i = itemList.length;
        int j = capacity;
        LinkedList<KnapsackItem> solution = new LinkedList<>();
        while(i>0 && j>0){
            if(table[i][j] !=table[i-1][j]){
                solution.add(itemList[i-1]);
                j=j-itemList[i-1].getWeight();
            }
            i=i-1;
        }
        return solution;
    }

    public LinkedList<KnapsackItem> visualizeSolution(){
        int i = itemList.length;
        int j = capacity;
        LinkedList<KnapsackItem> solution = new LinkedList<>();
        while(i>0 && j>0){
            if(table[i][j] !=table[i-1][j]){

                System.out.println("\n Finding and adding item: " + itemList[i-1]);
                solution.add(itemList[i-1]);
                LinkedList<String> itemNames=new LinkedList<>();
                for (int z = 0; z < table.length; z++) {
                    if(z>=1){
                        itemNames.add(itemList[z-1].getName());
                    }
                    StringBuilder row = new StringBuilder();
                    if(z!=i){
                        row.append("{").append(itemNames.toString()).append("} ");
                        row.append(Arrays.toString(table[z]));
                    }else{
                        row.append("{");
                        for (int k = 0; k < itemNames.size()-1; k++) {
                            row.append(itemNames.get(k)).append(", ");
                        }
                        row.append("\u001B[33m").append(itemNames.get(itemNames.size() - 1)).append("\u001B[0m").append("} [");
                        for (int k = 0; k < capacity+1; k++) {
                            if(k!=j){
                                row.append(table[z][k]).append(", ");
                            }else{
                                row.append("\u001B[34m").append(table[z][k]).append("\u001B[0m").append(", ");
                            }
                        }
                        row =  new StringBuilder(row.substring(0,row.length()-2));
                        row.append("]");
                    }
                    System.out.println(row);
                }
                j=j-itemList[i-1].getWeight();

            }

            i=i-1;
        }
        return solution;
    }



    public void printTable(){ //Prints the table by showing the itemName in front of each row
        LinkedList<String> itemNames=new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if(i>=1){
                itemNames.add(itemList[i-1].getName());
            }
            String row = "{"+itemNames.toString()+"} " + Arrays.toString(table[i]) ;
            System.out.println(row);
        }
    }

}
