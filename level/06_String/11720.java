import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int n = keyboard.nextInt();
        String numsStr = keyboard.next();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += numsStr.charAt(i) - 48;
        }

        System.out.println(sum);
    }
}
