import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionHandlingJDBC {
    
    static class BankDAO {
        String dbURL = "jdbc:sqlite:bank.db";
        
        public void init() {
            try {
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(dbURL);
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE IF NOT EXISTS accounts (account_id INTEGER PRIMARY KEY, holder_name TEXT NOT NULL, balance REAL NOT NULL)");
                conn.close();
            } catch (Exception e) {}
        }
        
        public void transfer(int fromId, int toId, double amount) {
            try (Connection conn = DriverManager.getConnection(dbURL)) {
                conn.setAutoCommit(false);
                
                String checkSql = "SELECT balance FROM accounts WHERE account_id = ?";
                try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                    ps.setInt(1, fromId);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next() || rs.getDouble("balance") < amount) {
                        System.out.println("Insufficient balance");
                        conn.rollback();
                        return;
                    }
                }
                
                try (PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE account_id = ?")) {
                    ps.setDouble(1, amount);
                    ps.setInt(2, fromId);
                    ps.executeUpdate();
                }
                
                try (PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE account_id = ?")) {
                    ps.setDouble(1, amount);
                    ps.setInt(2, toId);
                    ps.executeUpdate();
                }
                
                conn.commit();
                System.out.println("Transfer successful: $" + amount + " from " + fromId + " to " + toId);
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        public void addAccount(String name, double balance) {
            try (Connection conn = DriverManager.getConnection(dbURL);
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts (holder_name, balance) VALUES (?, ?)")) {
                ps.setString(1, name);
                ps.setDouble(2, balance);
                ps.executeUpdate();
            } catch (Exception e) {}
        }
        
        public void display() {
            try (Connection conn = DriverManager.getConnection(dbURL);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {
                System.out.println("\nID\tName\t\tBalance");
                while (rs.next())
                    System.out.println(rs.getInt("account_id") + "\t" + rs.getString("holder_name") + "\t\t$" + rs.getDouble("balance"));
            } catch (Exception e) {}
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankDAO dao = new BankDAO();
        dao.init();
        dao.addAccount("Alice", 1000);
        dao.addAccount("Bob", 500);
        
        boolean running = true;
        while (running) {
            System.out.println("\n1. Transfer  2. View  3. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("From ID: ");
                    int fromId = scanner.nextInt();
                    System.out.print("To ID: ");
                    int toId = scanner.nextInt();
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    dao.transfer(fromId, toId, amount);
                    break;
                case 2:
                    dao.display();
                    break;
                case 3:
                    running = false;
            }
        }
        scanner.close();
    }
}
