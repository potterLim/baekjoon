import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int[] arr = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = keyboard.nextInt();
        }

        Arrays.sort(arr);

        int total_height = 0;
        for (int i = 0; i < 9; i++) {
            total_height += arr[i];
        }

        outerloop:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (total_height - (arr[i] + arr[j]) == 100) {
                    arr[i] = 101;
                    arr[j] = 101;
                    break outerloop;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (arr[i] > 100) {
                continue;
            }

            System.out.println(arr[i]);
        }
    }
}