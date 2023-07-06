import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int N = Integer.parseInt(keyboard.nextLine());

        for (int i = 0; i < N; i++) {
            String str = keyboard.nextLine();
            char first_char = str.charAt(0);
            char last_char = str.charAt(str.length() - 1);

            System.out.print(first_char);
            System.out.println(last_char);
        }
    }
}
