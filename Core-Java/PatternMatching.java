public class PatternMatching {
    
    static void checkType(Object obj) {
        String result;
        
        if (obj == null)
            result = "Null";
        else if (obj instanceof Integer i)
            result = "Integer: " + i + " (x2=" + (i * 2) + ")";
        else if (obj instanceof String s)
            result = "String: " + s + " (len=" + s.length() + ")";
        else if (obj instanceof Double d)
            result = "Double: " + d + " (round=" + Math.round(d) + ")";
        else if (obj instanceof Boolean b)
            result = "Boolean: " + b;
        else
            result = "Unknown: " + obj.getClass().getSimpleName();
        
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        checkType(42);
        checkType("Hello");
        checkType(3.14);
        checkType(true);
        checkType(null);
    }
}
