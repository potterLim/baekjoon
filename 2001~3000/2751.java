import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int n = keyboard.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>(n);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            arr.add(keyboard.nextInt());
        }

        Collections.sort(arr);

        for (int i : arr) {
            sb.append(i).append('\n');
        }

        System.out.println(sb);
    }
}