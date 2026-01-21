import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        int rectangleCount = Integer.parseInt(line.trim());
        VerticalEdge[] edges = new VerticalEdge[rectangleCount * 2];
        int[] yCoordinates = new int[rectangleCount * 2];

        for (int i = 0; i < rectangleCount; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            edges[i * 2] = new VerticalEdge(x1, y1, y2, 1);
            edges[i * 2 + 1] = new VerticalEdge(x2, y1, y2, -1);
            yCoordinates[i * 2] = y1;
            yCoordinates[i * 2 + 1] = y2;
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a.x, b.x));
        Arrays.sort(yCoordinates);

        int distinctYCount = 0;
        if (rectangleCount > 0) {
            distinctYCount = 1;
            for (int i = 1; i < yCoordinates.length; ++i) {
                if (yCoordinates[i] != yCoordinates[i - 1]) {
                    yCoordinates[distinctYCount] = yCoordinates[i];
                    distinctYCount++;
                }
            }
        }

        int[] compressedY = Arrays.copyOf(yCoordinates, distinctYCount);
        SegmentTree tree = new SegmentTree(compressedY);

        long totalArea = 0;
        for (int i = 0; i < edges.length - 1; ++i) {
            tree.update(edges[i].y1, edges[i].y2, edges[i].type, 1, 0, distinctYCount - 2);
            long width = edges[i + 1].x - edges[i].x;
            totalArea += width * tree.getRootLength();
        }

        System.out.println(totalArea);
    }

    private static class VerticalEdge {
        public int x;
        public int y1;
        public int y2;
        public int type;

        public VerticalEdge(int x, int y1, int y2, int type) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.type = type;
        }
    }

    private static class SegmentTree {
        private int[] count;
        private long[] length;
        private int[] yCoords;

        public SegmentTree(int[] yCoords) {
            this.yCoords = yCoords;
            int n = yCoords.length;
            this.count = new int[n * 4];
            this.length = new long[n * 4];
        }

        public void update(int targetY1, int targetY2, int diff, int node, int start, int end) {
            if (targetY2 <= yCoords[start] || targetY1 >= yCoords[end + 1]) {
                return;
            }

            if (targetY1 <= yCoords[start] && yCoords[end + 1] <= targetY2) {
                count[node] += diff;
            } else {
                int mid = (start + end) / 2;
                update(targetY1, targetY2, diff, node * 2, start, mid);
                update(targetY1, targetY2, diff, node * 2 + 1, mid + 1, end);
            }

            updateLength(node, start, end);
        }

        private void updateLength(int node, int start, int end) {
            if (count[node] > 0) {
                length[node] = yCoords[end + 1] - yCoords[start];
            } else {
                if (start == end) {
                    length[node] = 0;
                } else {
                    length[node] = length[node * 2] + length[node * 2 + 1];
                }
            }
        }

        public long getRootLength() {
            return length[1];
        }
    }
}