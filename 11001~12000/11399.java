import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i< n; i++) {
            arr[i] = keyboard.nextInt();
        }
        
        Arrays.sort(arr);

        int total_time = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j<=i; j++) {
                total_time += arr[j];
            }
        }
        
        System.out.println(total_time);
    }
}