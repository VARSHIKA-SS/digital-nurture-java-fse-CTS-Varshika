import java.lang.reflect.Method;

public class ReflectionInJava {
    
    static class Calculator {
        public int add(int a, int b) { return a + b; }
        public int subtract(int a, int b) { return a - b; }
        public int multiply(int a, int b) { return a * b; }
        public double divide(double a, double b) { return a / b; }
    }
    
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("ReflectionInJava$Calculator");
            System.out.println("Class: " + clazz.getName());
            System.out.println("\nMethods:");
            
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("- " + method.getName() + "()");
                if (method.getName().equals("add")) {
                    Object instance = clazz.newInstance();
                    Object result = method.invoke(instance, 10, 5);
                    System.out.println("  Result: " + result);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
