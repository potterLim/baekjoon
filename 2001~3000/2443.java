import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int N = keyboard.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                if(j >= 2 * N - 1 - i) {
                    break;
                }
               if (j < i) {
                   System.out.print(" ");
               } else {
                   System.out.print("*");
               }
            }
            System.out.println();
        }
    }
}