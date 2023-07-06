import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int result = 0;
        String num_str = keyboard.nextLine();

        for (int i = 0; i < num_str.length(); ++i) {
            result += Math.pow(Integer.parseInt(String.valueOf(num_str.charAt(i))), 5);
        }

        System.out.println(result);
    }
}