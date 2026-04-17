import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String[] enterOrder = new String[n];
        String[] exitOrder = new String[n];
        HashMap<String, Integer> enterIndexMap = new HashMap<String, Integer>();

        for (int i = 0; i < n; ++i) {
            enterOrder[i] = scanner.next();
            enterIndexMap.put(enterOrder[i], i);
        }

        for (int i = 0; i < n; ++i) {
            exitOrder[i] = scanner.next();
        }

        boolean[] isExited = new boolean[n];
        int answer = 0;
        int front = 0;

        for (int i = 0; i < n; ++i) {
            int currentIndex = enterIndexMap.get(exitOrder[i]);

            while (front < n && isExited[front]) {
                ++front;
            }

            if (currentIndex != front) {
                ++answer;
            }

            isExited[currentIndex] = true;
        }

        System.out.println(answer);
        scanner.close();
    }
}