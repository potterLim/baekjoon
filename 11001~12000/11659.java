import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int N = Integer.parseInt(keyboard.next());
        int M = Integer.parseInt(keyboard.next());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                arr[i] = Integer.parseInt(keyboard.next());
            } else {
                arr[i] = arr[i - 1] + Integer.parseInt(keyboard.next());
            }
        }

        for (int k = 0; k < M; k++) {
            int i = Integer.parseInt(keyboard.next()) - 1;
            int j = Integer.parseInt(keyboard.next()) - 1;
            if (i == 0) {
                System.out.println(arr[j]);
            } else {
                System.out.println(arr[j] - arr[i - 1]);
            }
        }

    }
}