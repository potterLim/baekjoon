import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public final class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            int[] positions = new int[n];
            for (int i = 0; i < n; ++i) {
                positions[i] = Integer.parseInt(reader.readLine().trim());
            }

            Arrays.sort(positions);

            int low = 1;
            int high = positions[n - 1] - positions[0];
            int answer = 0;

            while (low <= high) {
                int mid = low + ((high - low) >>> 1);

                if (canPlaceRouters(positions, c, mid)) {
                    answer = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            System.out.println(answer);
        }
    }

    private static boolean canPlaceRouters(int[] positions, int routerCount, int minDistance) {
        int count = 1;
        int last = positions[0];

        for (int i = 1; i < positions.length; ++i) {
            if (positions[i] - last >= minDistance) {
                ++count;
                last = positions[i];

                if (count >= routerCount) {
                    return true;
                }
            }
        }

        return false;
    }
}
