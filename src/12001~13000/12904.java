import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String targetStr = scanner.next();
        String fullStr = scanner.next();

        StringBuilder currentStr = new StringBuilder(fullStr);

        while (currentStr.length() > targetStr.length()) {
            char lastChar = currentStr.charAt(currentStr.length() - 1);

            if (lastChar == 'A') {
                currentStr.deleteCharAt(currentStr.length() - 1);
            } else if (lastChar == 'B') {
                currentStr.deleteCharAt(currentStr.length() - 1);
                currentStr.reverse();
            } else {
                break;
            }
        }

        if (currentStr.toString().equals(targetStr)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}