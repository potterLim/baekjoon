import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        public int index;
        public int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    private static List<List<Node>> adjacencyList;
    private static boolean[] visited;
    private static int maxDistance;
    private static int farthestNode;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int vertexCount = Integer.parseInt(reader.readLine());

        adjacencyList = new ArrayList<>();
        for (int i = 0; i <= vertexCount; ++i) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < vertexCount; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());

            while (true) {
                int to = Integer.parseInt(tokenizer.nextToken());
                if (to == -1) {
                    break;
                }
                int distance = Integer.parseInt(tokenizer.nextToken());
                adjacencyList.get(from).add(new Node(to, distance));
            }
        }

        visited = new boolean[vertexCount + 1];
        maxDistance = 0;
        dfs(1, 0);

        visited = new boolean[vertexCount + 1];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    private static void dfs(int currentNode, int accumulatedCost) {
        visited[currentNode] = true;

        if (accumulatedCost > maxDistance) {
            maxDistance = accumulatedCost;
            farthestNode = currentNode;
        }

        for (Node neighbor : adjacencyList.get(currentNode)) {
            if (!visited[neighbor.index]) {
                dfs(neighbor.index, accumulatedCost + neighbor.cost);
            }
        }
    }
}