import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String formula = scanner.nextLine();

        final Stack<Object> stack = new Stack<>();

        for (int i = 0; i < formula.length(); i++) {
            final char ch = formula.charAt(i);

            if (ch == 'H') {
                stack.push(1);
            } else if (ch == 'C') {
                stack.push(12);
            } else if (ch == 'O') {
                stack.push(16);
            } else if (ch == '(') {
                stack.push('(');
            } else if (ch == ')') {
                int sum = 0;
                while (!stack.isEmpty()) {
                    final Object top = stack.pop();
                    if (top instanceof Character && (char) top == '(') {
                        break;
                    }
                    sum += (int) top;
                }
                stack.push(sum);
            } else if (Character.isDigit(ch)) {
                final int num = ch - '0';
                final Object top = stack.pop();
                if (top instanceof Integer) {
                    stack.push((int) top * num);
                }
            }
        }

        int total = 0;
        for (final Object obj : stack) {
            total += (int) obj;
        }

        System.out.println(total);
    }
}
