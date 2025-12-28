import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static final class Edge {
        private final int to;
        private final int count;

        private Edge(int to, int count) {
            this.to = to;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int partCount = Integer.parseInt(reader.readLine());
        int relationCount = Integer.parseInt(reader.readLine());

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[partCount + 1];
        for (int i = 1; i <= partCount; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[partCount + 1];

        for (int i = 0; i < relationCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int product = Integer.parseInt(tokenizer.nextToken());
            int component = Integer.parseInt(tokenizer.nextToken());
            int count = Integer.parseInt(tokenizer.nextToken());

            graph[component].add(new Edge(product, count));
            indegree[product]++;
        }

        long[][] required = new long[partCount + 1][partCount + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= partCount; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                required[i][i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();

            for (Edge edge : graph[current]) {
                int next = edge.to;

                for (int i = 1; i <= partCount; i++) {
                    if (required[current][i] > 0) {
                        required[next][i] += required[current][i] * edge.count;
                    }
                }

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= partCount; i++) {
            if (required[i][i] > 0 && required[partCount][i] > 0) {
                result.append(i).append(' ').append(required[partCount][i]).append('\n');
            }
        }

        System.out.print(result.toString());
    }
}
