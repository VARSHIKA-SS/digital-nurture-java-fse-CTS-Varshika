import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        
        System.out.println("Student Management System");
        
        boolean running = true;
        while (running) {
            System.out.print("Enter name (or 'quit'): ");
            String name = scanner.nextLine();
            
            if (name.equalsIgnoreCase("quit")) {
                running = false;
            } else if (!name.trim().isEmpty()) {
                names.add(name);
            }
        }
        
        System.out.println("\nAll Students:");
        for (int i = 0; i < names.size(); i++)
            System.out.println((i + 1) + ". " + names.get(i));
        
        scanner.close();
    }
}
