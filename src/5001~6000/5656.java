import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder resultBuilder = new StringBuilder();

        int caseNumber = 1;

        while (true) {
            int leftValue = scanner.nextInt();
            String operator = scanner.next();
            int rightValue = scanner.nextInt();

            if (operator.equals("E")) {
                break;
            }

            boolean isTrue = false;

            if (operator.equals(">")) {
                isTrue = leftValue > rightValue;
            } else if (operator.equals(">=")) {
                isTrue = leftValue >= rightValue;
            } else if (operator.equals("<")) {
                isTrue = leftValue < rightValue;
            } else if (operator.equals("<=")) {
                isTrue = leftValue <= rightValue;
            } else if (operator.equals("==")) {
                isTrue = leftValue == rightValue;
            } else if (operator.equals("!=")) {
                isTrue = leftValue != rightValue;
            }

            resultBuilder.append("Case ")
                    .append(caseNumber)
                    .append(": ");

            if (isTrue) {
                resultBuilder.append("true");
            } else {
                resultBuilder.append("false");
            }

            resultBuilder.append('\n');
            ++caseNumber;
        }

        System.out.print(resultBuilder);
    }
}