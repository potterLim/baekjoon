import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static final class Node {
        private final TreeMap<String, Node> children;

        public Node() {
            this.children = new TreeMap<String, Node>();
        }

        public Node getOrCreateChild(String key) {
            Node child;

            child = children.get(key);
            if (child != null) {
                return child;
            }

            child = new Node();
            children.put(key, child);
            return child;
        }

        public void print(StringBuilder output, int depth) {
            for (Map.Entry<String, Node> entry : children.entrySet()) {
                for (int i = 0; i < depth; ++i) {
                    output.append("--");
                }

                output.append(entry.getKey()).append('\n');
                entry.getValue().print(output, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner;
        int pathCount;
        Node root;
        StringBuilder result;

        scanner = new Scanner(System.in);
        pathCount = scanner.nextInt();

        root = new Node();

        for (int i = 0; i < pathCount; ++i) {
            int depth;
            Node current;

            depth = scanner.nextInt();
            current = root;

            for (int j = 0; j < depth; ++j) {
                String food;

                food = scanner.next();
                current = current.getOrCreateChild(food);
            }
        }

        result = new StringBuilder();
        root.print(result, 0);
        System.out.print(result.toString());
    }
}