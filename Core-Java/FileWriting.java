import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter text: ");
            String text = scanner.nextLine();
            
            FileWriter writer = new FileWriter("output.txt");
            writer.write(text);
            writer.close();
            
            System.out.println("Data written to output.txt");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
