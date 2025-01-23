import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] seniors = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            seniors[i] = scanner.nextInt();
        }
        scanner.close();

        int result = findBestSenior(n, seniors);
        System.out.println(result);
    }

    private static int findBestSenior(int n, int[] seniors) {
        int maxVisited = 0;
        int bestSenior = 1;

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            int count = 0;
            int current = i;

            while (!visited[current]) {
                visited[current] = true;
                count++;
                current = seniors[current];
            }

            if (count > maxVisited || (count == maxVisited && i < bestSenior)) {
                maxVisited = count;
                bestSenior = i;
            }
        }

        return bestSenior;
    }
}