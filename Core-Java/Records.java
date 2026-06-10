import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Records {
    
    record Person(String name, int age) {}
    
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 17));
        people.add(new Person("Charlie", 30));
        people.add(new Person("Diana", 16));
        people.add(new Person("Eve", 22));
        
        System.out.println("All people: ");
        people.forEach(System.out::println);
        
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());
        System.out.println("\nAdults (18+): ");
        adults.forEach(System.out::println);
        
        List<Person> minors = people.stream()
                                   .filter(p -> p.age() < 18)
                                   .collect(Collectors.toList());
        System.out.println("\nMinors (<18): ");
        minors.forEach(System.out::println);
    }
}
