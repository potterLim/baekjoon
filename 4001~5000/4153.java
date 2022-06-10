import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int[] length = new int[3];

        while (true) {
            for(int i = 0; i < 3; i++) {
                length[i] = keyboard.nextInt();
            }

            if (length[0] == 0 && length[1] == 0 && length[2] == 0) {
                break;
            }

            Arrays.sort(length);

            if (Math.pow(length[0], 2) + Math.pow(length[1], 2) == Math.pow(length[2], 2)) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}