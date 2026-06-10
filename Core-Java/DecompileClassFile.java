public class DecompileClassFile {
    
    private int value;
    private String message;
    
    public DecompileClassFile(int value, String message) {
        this.value = value;
        this.message = message;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void printInfo() {
        System.out.println("Value: " + value + ", Message: " + message);
    }
    
    public static void main(String[] args) {
        DecompileClassFile obj = new DecompileClassFile(42, "Hello");
        obj.printInfo();
        
        System.out.println("\nDecompilation tools:");
        System.out.println("cfr DecompileClassFile.class");
        System.out.println("javap -c DecompileClassFile.class");
    }
}
