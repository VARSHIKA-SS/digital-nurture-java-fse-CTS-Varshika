import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            numbers.add(i);
        
        System.out.println("All numbers: " + numbers);
        
        List<Integer> even = numbers.stream()
                                    .filter(n -> n % 2 == 0)
                                    .collect(Collectors.toList());
        System.out.println("Even numbers: " + even);
        
        List<Integer> odd = numbers.stream()
                                   .filter(n -> n % 2 != 0)
                                   .collect(Collectors.toList());
        System.out.println("Odd numbers: " + odd);
    }
}
