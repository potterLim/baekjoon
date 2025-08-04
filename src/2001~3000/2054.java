import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static List<String> result = new ArrayList<>();
    static String input;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        dfs(0, "");
        Collections.sort(result);
        for (String expr : result) {
            System.out.println(expr);
        }
    }

    static void dfs(int idx, String expr) {
        int len = input.length();
        if (idx == len) {
            if (isValidExpression(expr)) {
                if (evaluate(expr) == 2000) {
                    result.add(expr);
                }
            }
            return;
        }

        for (int i = idx + 1; i <= len; ++i) {
            String num = input.substring(idx, i);
            if (!isValidNumber(num)) {
                continue;
            }

            if (idx == 0) {
                dfs(i, num);
            } else {
                dfs(i, expr + "+" + num);
                dfs(i, expr + "-" + num);
                dfs(i, expr + "*" + num);
            }
        }
    }

    static boolean isValidNumber(String s) {
        if (s.length() == 0) return false;
        if (s.equals("0")) return true;
        return s.charAt(0) != '0';
    }

    static boolean isValidExpression(String expr) {
        String[] tokens = expr.split("[+\\-*]");
        for (String token : tokens) {
            if (!isValidNumber(token)) {
                return false;
            }
        }
        return true;
    }

    static long evaluate(String expr) {
        List<Long> values = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int i = 0;
        int len = expr.length();

        while (i < len) {
            int j = i;
            while (j < len && Character.isDigit(expr.charAt(j))) {
                j++;
            }
            long num = Long.parseLong(expr.substring(i, j));
            values.add(num);
            if (j < len) {
                ops.add(expr.charAt(j));
            }
            i = j + 1;
        }

        for (int k = 0; k < ops.size(); ) {
            if (ops.get(k) == '*') {
                long multiplied = values.get(k) * values.get(k + 1);
                values.set(k, multiplied);
                values.remove(k + 1);
                ops.remove(k);
            } else {
                k++;
            }
        }

        long res = values.get(0);
        for (int k = 0; k < ops.size(); ++k) {
            if (ops.get(k) == '+') {
                res += values.get(k + 1);
            } else {
                res -= values.get(k + 1);
            }
        }

        return res;
    }
}
