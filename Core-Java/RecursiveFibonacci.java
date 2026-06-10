import java.util.Scanner;

public class RecursiveFibonacci {
    
    public static long fibonacci(int n) {
        return (n <= 1) ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        
        if (n < 0) {
            System.out.println("Enter a non-negative number");
            return;
        }
        System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));
        scanner.close();
    }
}
