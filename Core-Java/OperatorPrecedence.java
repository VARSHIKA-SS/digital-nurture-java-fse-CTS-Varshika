public class OperatorPrecedence {
    public static void main(String[] args) {
        System.out.println("10 + 5 * 2 = " + (10 + 5 * 2) + " (mult before add)");
        System.out.println("(10 + 5) * 2 = " + ((10 + 5) * 2) + " (parentheses first)");
        System.out.println("20 - 8 / 2 + 3 = " + (20 - 8 / 2 + 3) + " (div before sub/add)");
        System.out.println("15 * 2 / 3 + 1 = " + (15 * 2 / 3 + 1) + " (left to right)");
    }
}
