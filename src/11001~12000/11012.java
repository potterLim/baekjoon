import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        int testCaseCount = Integer.parseInt(line.trim());
        for (int t = 0; t < testCaseCount; ++t) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int folkCount = Integer.parseInt(st.nextToken());
            int paradeCount = Integer.parseInt(st.nextToken());

            Point[] points = new Point[folkCount];
            for (int i = 0; i < folkCount; ++i) {
                st = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            Arrays.sort(points, (a, b) -> Integer.compare(a.x, b.x));

            QueryEvent[] events = new QueryEvent[paradeCount * 2];
            for (int i = 0; i < paradeCount; ++i) {
                st = new StringTokenizer(reader.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int top = Integer.parseInt(st.nextToken());

                events[i * 2] = new QueryEvent(i, l - 1, b, top, -1);
                events[i * 2 + 1] = new QueryEvent(i, r, b, top, 1);
            }

            Arrays.sort(events, (a, b) -> Integer.compare(a.x, b.x));

            FenwickTree tree = new FenwickTree(100001);
            long[] queryResults = new long[paradeCount];
            int pointIndex = 0;

            for (int i = 0; i < events.length; ++i) {
                while (pointIndex < folkCount && points[pointIndex].x <= events[i].x) {
                    tree.update(points[pointIndex].y + 1, 1);
                    pointIndex++;
                }

                int countInRange = tree.query(events[i].top + 1) - tree.query(events[i].bottom);
                queryResults[events[i].id] += (long) countInRange * events[i].weight;
            }

            long totalEggs = 0;
            for (int i = 0; i < paradeCount; ++i) {
                totalEggs += queryResults[i];
            }

            System.out.println(totalEggs);
        }
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class QueryEvent {
        public int id;
        public int x;
        public int bottom;
        public int top;
        public int weight;

        public QueryEvent(int id, int x, int bottom, int top, int weight) {
            this.id = id;
            this.x = x;
            this.bottom = bottom;
            this.top = top;
            this.weight = weight;
        }
    }

    private static class FenwickTree {
        private int[] tree;
        private int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        public void update(int index, int delta) {
            if (index <= 0) {
                return;
            }
            while (index <= size) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        public int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
}