import java.util.Scanner;

public class Main {
    private static final class Node {
        private final char mValue;
        private final Node mLeft;
        private final Node mRight;

        public Node(char value) {
            mValue = value;
            mLeft = null;
            mRight = null;
        }

        public Node(char value, Node left, Node right) {
            mValue = value;
            mLeft = left;
            mRight = right;
        }
    }

    private static char[] sExpression;
    private static int sIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        scanner.close();

        sExpression = input.toCharArray();
        sIndex = 0;

        Node root = parseExpression();
        StringBuilder builder = new StringBuilder();

        appendExpression(root, builder);

        System.out.print(builder.toString());
    }

    private static Node parseExpression() {
        Node left = parseTerm();

        while (sIndex < sExpression.length) {
            char op = sExpression[sIndex];

            if (op != '+' && op != '-') {
                break;
            }

            ++sIndex;
            Node right = parseTerm();
            left = new Node(op, left, right);
        }

        return left;
    }

    private static Node parseTerm() {
        Node left = parseFactor();

        while (sIndex < sExpression.length) {
            char op = sExpression[sIndex];

            if (op != '*' && op != '/') {
                break;
            }

            ++sIndex;
            Node right = parseFactor();
            left = new Node(op, left, right);
        }

        return left;
    }

    private static Node parseFactor() {
        char ch = sExpression[sIndex];

        if (ch >= 'a' && ch <= 'z') {
            ++sIndex;
            return new Node(ch);
        }

        ++sIndex;
        Node node = parseExpression();
        ++sIndex;

        return node;
    }

    private static void appendExpression(Node node, StringBuilder builder) {
        boolean[] isFirst = new boolean[1];
        isFirst[0] = true;
        appendExpression(node, false, builder, isFirst);
    }

    private static void appendExpression(Node node, boolean isNegative, StringBuilder builder, boolean[] isFirst) {
        if (node.mValue == '+') {
            appendExpression(node.mLeft, isNegative, builder, isFirst);
            appendExpression(node.mRight, isNegative, builder, isFirst);
            return;
        }

        if (node.mValue == '-') {
            appendExpression(node.mLeft, isNegative, builder, isFirst);
            appendExpression(node.mRight, !isNegative, builder, isFirst);
            return;
        }

        if (isFirst[0]) {
            if (isNegative) {
                builder.append('-');
            }

            isFirst[0] = false;
        } else {
            if (isNegative) {
                builder.append('-');
            } else {
                builder.append('+');
            }
        }

        appendTerm(node, builder);
    }

    private static void appendTerm(Node node, StringBuilder builder) {
        boolean[] isFirst = new boolean[1];
        isFirst[0] = true;
        appendTerm(node, false, builder, isFirst);
    }

    private static void appendTerm(Node node, boolean isDivide, StringBuilder builder, boolean[] isFirst) {
        if (node.mValue == '*') {
            appendTerm(node.mLeft, isDivide, builder, isFirst);
            appendTerm(node.mRight, isDivide, builder, isFirst);
            return;
        }

        if (node.mValue == '/') {
            appendTerm(node.mLeft, isDivide, builder, isFirst);
            appendTerm(node.mRight, !isDivide, builder, isFirst);
            return;
        }

        if (isFirst[0]) {
            if (isDivide) {
                builder.append('/');
            }

            isFirst[0] = false;
        } else {
            if (isDivide) {
                builder.append('/');
            } else {
                builder.append('*');
            }
        }

        appendFactor(node, builder);
    }

    private static void appendFactor(Node node, StringBuilder builder) {
        if (node.mValue == '+' || node.mValue == '-') {
            builder.append('(');
            appendExpression(node, builder);
            builder.append(')');
            return;
        }

        if (node.mValue == '*' || node.mValue == '/') {
            appendTerm(node, builder);
            return;
        }

        builder.append(node.mValue);
    }
}