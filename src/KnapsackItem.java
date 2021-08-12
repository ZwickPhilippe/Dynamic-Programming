public class KnapsackItem {

    private int weight;
    private int value;
    private String name;

    public KnapsackItem(int weight, int value, String name){
        this.weight=weight;
        this.value=value;
        this.name=name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "KnapsackItem{" +
                "weight=" + weight +
                ", value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
