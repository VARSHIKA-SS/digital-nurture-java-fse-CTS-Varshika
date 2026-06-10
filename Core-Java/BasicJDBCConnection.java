import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicJDBCConnection {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            Statement stmt = conn.createStatement();
            
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, marks REAL)");
            stmt.executeUpdate("INSERT INTO students (name, marks) VALUES ('John', 85)");
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            System.out.println("Students:");
            while (rs.next())
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Marks: " + rs.getDouble("marks"));
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
