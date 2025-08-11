import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int pointCount = Integer.parseInt(tokenizer.nextToken());

        int[][] points = new int[pointCount][2];

        for (int i = 0; i < pointCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            points[i][0] = x;
            points[i][1] = y;
        }

        Arrays.sort(points, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pointCount; i++) {
            sb.append(points[i][0]).append(' ').append(points[i][1]).append('\n');
        }

        System.out.print(sb.toString());
    }
}
