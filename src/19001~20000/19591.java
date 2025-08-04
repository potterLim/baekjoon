import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expr = scanner.nextLine();

        Deque<Long> numberDeque = new ArrayDeque<>();
        Deque<Character> operatorDeque = new ArrayDeque<>();

        int i = 0;
        int n = expr.length();
        while (i < n) {
            boolean negative = false;
            if (expr.charAt(i) == '-' && (i == 0 || "+-*/".indexOf(expr.charAt(i - 1)) != -1)) {
                negative = true;
                i++;
            }

            int start = i;
            while (i < n && Character.isDigit(expr.charAt(i))) {
                i++;
            }

            long num = Long.parseLong(expr.substring(start, i));
            if (negative) {
                num = -num;
            }
            numberDeque.addLast(num);

            if (i < n) {
                operatorDeque.addLast(expr.charAt(i));
                i++;
            }
        }

        while (!operatorDeque.isEmpty()) {
            char frontOp = operatorDeque.peekFirst();
            char backOp = operatorDeque.peekLast();

            int frontPri = getPriority(frontOp);
            int backPri = getPriority(backOp);

            boolean useFront;

            if (frontPri > backPri) {
                useFront = true;
            } else if (frontPri < backPri) {
                useFront = false;
            } else {
                long a1 = numberDeque.peekFirst();
                long a2 = getSecond(numberDeque, true);
                long frontResult = calculate(a1, a2, frontOp);

                long b2 = numberDeque.peekLast();
                long b1 = getSecond(numberDeque, false);
                long backResult = calculate(b1, b2, backOp);

                if (frontResult > backResult) {
                    useFront = true;
                } else if (frontResult < backResult) {
                    useFront = false;
                } else {
                    useFront = true;
                }
            }

            if (useFront) {
                long a = numberDeque.pollFirst();
                long b = numberDeque.pollFirst();
                char op = operatorDeque.pollFirst();
                numberDeque.addFirst(calculate(a, b, op));
            } else {
                long b = numberDeque.pollLast();
                long a = numberDeque.pollLast();
                char op = operatorDeque.pollLast();
                numberDeque.addLast(calculate(a, b, op));
            }
        }

        System.out.println(numberDeque.peek());
    }

    private static long getSecond(Deque<Long> dq, boolean front) {
        if (front) {
            long first = dq.pollFirst();
            long second = dq.peekFirst();
            dq.addFirst(first);
            return second;
        } else {
            long last = dq.pollLast();
            long second = dq.peekLast();
            dq.addLast(last);
            return second;
        }
    }

    private static int getPriority(char op) {
        if (op == '*' || op == '/') {
            return 2;
        }
        return 1;
    }

    private static long calculate(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: return a / b;
        }
    }
}
