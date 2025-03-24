import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static SegmentTree segmentTree;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());

        arr = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int index = 0; index < n; ++index) {
            arr[index] = Integer.parseInt(tokenizer.nextToken());
        }

        segmentTree = new SegmentTree(arr, n);

        tokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(tokenizer.nextToken());

        StringBuilder outputBuilder = new StringBuilder();

        for (int queryCount = 0; queryCount < m; ++queryCount) {
            tokenizer = new StringTokenizer(reader.readLine());
            int queryType = Integer.parseInt(tokenizer.nextToken());
            int i = Integer.parseInt(tokenizer.nextToken());
            int j = Integer.parseInt(tokenizer.nextToken());

            if (queryType == 1) {
                segmentTree.updateIndexValue(i - 1, j);
            } else if (queryType == 2) {
                int minValue = segmentTree.getRangeMinimum(i - 1, j - 1);
                outputBuilder.append(minValue).append("\n");
            }
        }

        System.out.print(outputBuilder);
    }

    private static class SegmentTree {
        private int[] tree;
        private int[] originArr;
        private int length;

        public SegmentTree(int[] sourceArr, int size) {
            this.length = size;
            this.originArr = new int[size];
            for (int index = 0; index < size; ++index) {
                this.originArr[index] = sourceArr[index];
            }

            this.tree = new int[size * 4];
            buildSegmentTreeRecursive(1, 0, size - 1);
        }

        public void updateIndexValue(int targetIndex, int newValue) {
            originArr[targetIndex] = newValue;
            updateSegmentTreeRecursive(1, 0, length - 1, targetIndex, newValue);
        }

        public int getRangeMinimum(int left, int right) {
            return querySegmentTreeRecursive(1, 0, length - 1, left, right);
        }

        private void buildSegmentTreeRecursive(int nodeIndex, int start, int end) {
            if (start == end) {
                tree[nodeIndex] = originArr[start];
                return;
            }

            int mid = (start + end) / 2;
            buildSegmentTreeRecursive(nodeIndex * 2, start, mid);
            buildSegmentTreeRecursive(nodeIndex * 2 + 1, mid + 1, end);
            tree[nodeIndex] = Math.min(tree[nodeIndex * 2], tree[nodeIndex * 2 + 1]);
        }

        private void updateSegmentTreeRecursive(int nodeIndex, int start, int end,
                                                int targetIndex, int newValue) {
            if (targetIndex < start || targetIndex > end) {
                return;
            }

            if (start == end) {
                tree[nodeIndex] = newValue;
                return;
            }

            int mid = (start + end) / 2;
            if (targetIndex <= mid) {
                updateSegmentTreeRecursive(nodeIndex * 2, start, mid, targetIndex, newValue);
            } else {
                updateSegmentTreeRecursive(nodeIndex * 2 + 1, mid + 1, end, targetIndex, newValue);
            }

            tree[nodeIndex] = Math.min(tree[nodeIndex * 2], tree[nodeIndex * 2 + 1]);
        }

        private int querySegmentTreeRecursive(int nodeIndex, int start, int end,
                                              int left, int right) {
            if (right < start || left > end) {
                return Integer.MAX_VALUE;
            }

            if (left <= start && end <= right) {
                return tree[nodeIndex];
            }

            int mid = (start + end) / 2;
            int leftResult = querySegmentTreeRecursive(nodeIndex * 2, start, mid, left, right);
            int rightResult = querySegmentTreeRecursive(nodeIndex * 2 + 1, mid + 1, end, left, right);
            return Math.min(leftResult, rightResult);
        }
    }
}
