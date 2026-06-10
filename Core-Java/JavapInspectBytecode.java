public class JavapInspectBytecode {
    
    private int id;
    private String name;
    
    public JavapInspectBytecode(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
    
    public static void main(String[] args) {
        JavapInspectBytecode obj = new JavapInspectBytecode(1, "John");
        obj.displayInfo();
        
        System.out.println("\nTo inspect bytecode:");
        System.out.println("javap -c JavapInspectBytecode");
        System.out.println("javap -v JavapInspectBytecode");
    }
}
