import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Cherry");
        fruits.add("Date");
        
        System.out.println("Original: ");
        fruits.forEach(System.out::println);
        
        Collections.sort(fruits, (a, b) -> a.compareTo(b));
        System.out.println("\nSorted (Ascending):");
        fruits.forEach(System.out::println);
        
        Collections.sort(fruits, (a, b) -> b.compareTo(a));
        System.out.println("\nSorted (Descending):");
        fruits.forEach(System.out::println);
    }
}
