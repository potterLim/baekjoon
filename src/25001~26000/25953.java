import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int INF = 1_000_000_007;

    private static final class Edge {
        private final int fromVertex;
        private final int toVertex;
        private final int weight;

        private Edge(int fromVertex, int toVertex, int weight) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertexCount = scanner.nextInt();
        int timeCount = scanner.nextInt();
        int edgeCountPerTime = scanner.nextInt();
        int startVertex = scanner.nextInt();
        int endVertex = scanner.nextInt();

        List<Edge>[] edgesByTime = new ArrayList[timeCount];
        for (int i = 0; i < timeCount; i++) {
            edgesByTime[i] = new ArrayList<>();
        }

        for (int i = 0; i < timeCount; i++) {
            for (int j = 0; j < edgeCountPerTime; j++) {
                int fromVertex = scanner.nextInt();
                int toVertex = scanner.nextInt();
                int weight = scanner.nextInt();
                edgesByTime[i].add(new Edge(fromVertex, toVertex, weight));
            }
        }

        int[] previousDistance = new int[vertexCount];
        int[] currentDistance = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            previousDistance[i] = INF;
        }
        previousDistance[startVertex] = 0;

        for (int i = 0; i < timeCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                currentDistance[j] = previousDistance[j];
            }

            List<Edge> edges = edgesByTime[i];
            for (int j = 0; j < edges.size(); j++) {
                Edge edge = edges.get(j);

                int from = edge.fromVertex;
                int to = edge.toVertex;
                int weight = edge.weight;

                if (previousDistance[from] != INF) {
                    int candidate = previousDistance[from] + weight;
                    if (candidate < currentDistance[to]) {
                        currentDistance[to] = candidate;
                    }
                }

                if (previousDistance[to] != INF) {
                    int candidate = previousDistance[to] + weight;
                    if (candidate < currentDistance[from]) {
                        currentDistance[from] = candidate;
                    }
                }
            }

            int[] temp = previousDistance;
            previousDistance = currentDistance;
            currentDistance = temp;
        }

        int answer = previousDistance[endVertex];
        if (answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
