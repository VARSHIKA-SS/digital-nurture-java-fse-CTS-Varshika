import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class InsertAndUpdateJDBC {
    
    static class StudentDAO {
        String dbURL = "jdbc:sqlite:students.db";
        
        public void insert(String name, double marks) {
            try (Connection conn = DriverManager.getConnection(dbURL);
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, marks) VALUES (?, ?)")) {
                ps.setString(1, name);
                ps.setDouble(2, marks);
                ps.executeUpdate();
                System.out.println("Inserted successfully");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        public void update(int id, double marks) {
            try (Connection conn = DriverManager.getConnection(dbURL);
                 PreparedStatement ps = conn.prepareStatement("UPDATE students SET marks = ? WHERE id = ?")) {
                ps.setDouble(1, marks);
                ps.setInt(2, id);
                if (ps.executeUpdate() > 0)
                    System.out.println("Updated successfully");
                else
                    System.out.println("ID not found");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        public void display() {
            try (Connection conn = DriverManager.getConnection(dbURL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
                System.out.println("\nID\tName\t\tMarks");
                while (rs.next())
                    System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t\t" + rs.getDouble("marks"));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT NOT NULL, marks REAL NOT NULL)");
            conn.close();
        } catch (Exception e) {}
        
        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        
        boolean running = true;
        while (running) {
            System.out.println("\n1. Insert  2. Update  3. Display  4. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Marks: ");
                    double marks = scanner.nextDouble();
                    dao.insert(name, marks);
                    break;
                case 2:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    System.out.print("New marks: ");
                    double newMarks = scanner.nextDouble();
                    dao.update(id, newMarks);
                    break;
                case 3:
                    dao.display();
                    break;
                case 4:
                    running = false;
            }
        }
        scanner.close();
    }
}
