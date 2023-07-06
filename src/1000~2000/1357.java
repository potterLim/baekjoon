import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();
        int result = Rev(Rev(x) + Rev(y));
        System.out.println(result);
    }

    public static int Rev(int num) {
        String numStr = Integer.toString(num);
        int reverseNum = 0;
        int numLen = numStr.length();

        for (int i = 0; i < numLen; i++) {
            reverseNum += (numStr.charAt(i) - '0') * Math.pow(10, i);
        }
        return reverseNum;
    }
}
