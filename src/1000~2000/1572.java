import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.TreeMap;

public class Main {
    private static final TreeMap<Integer, Integer> leftHeap = new TreeMap<>();
    private static final TreeMap<Integer, Integer> rightHeap = new TreeMap<>();
    private static int leftSize = 0;
    private static int rightSize = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String[] firstLine = reader.readLine().split(" ");
        final int totalCount = Integer.parseInt(firstLine[0]);
        final int windowSize = Integer.parseInt(firstLine[1]);

        final int[] sequence = new int[totalCount];
        for (int i = 0; i < totalCount; ++i) {
            sequence[i] = Integer.parseInt(reader.readLine());
        }

        long medianSum = 0;

        for (int i = 0; i < totalCount; ++i) {
            addNumber(sequence[i]);
            if (i >= windowSize) {
                removeNumber(sequence[i - windowSize]);
            }

            if (i >= windowSize - 1) {
                medianSum += leftHeap.lastKey();
            }
        }

        System.out.println(medianSum);
    }

    private static void addNumber(int value) {
        if (leftSize == 0 || value <= leftHeap.lastKey()) {
            leftHeap.put(value, leftHeap.getOrDefault(value, 0) + 1);
            ++leftSize;
        } else {
            rightHeap.put(value, rightHeap.getOrDefault(value, 0) + 1);
            ++rightSize;
        }
        balanceHeaps();
    }

    private static void removeNumber(int value) {
        if (leftHeap.containsKey(value)) {
            int count = leftHeap.get(value);
            if (count == 1) {
                leftHeap.remove(value);
            } else {
                leftHeap.put(value, count - 1);
            }
            --leftSize;
        } else {
            int count = rightHeap.get(value);
            if (count == 1) {
                rightHeap.remove(value);
            } else {
                rightHeap.put(value, count - 1);
            }
            --rightSize;
        }
        balanceHeaps();
    }

    private static void balanceHeaps() {
        while (leftSize > rightSize + 1) {
            int maxLeft = leftHeap.lastKey();
            int count = leftHeap.get(maxLeft);
            if (count == 1) {
                leftHeap.remove(maxLeft);
            } else {
                leftHeap.put(maxLeft, count - 1);
            }
            --leftSize;
            rightHeap.put(maxLeft, rightHeap.getOrDefault(maxLeft, 0) + 1);
            ++rightSize;
        }

        while (leftSize < rightSize) {
            int minRight = rightHeap.firstKey();
            int count = rightHeap.get(minRight);
            if (count == 1) {
                rightHeap.remove(minRight);
            } else {
                rightHeap.put(minRight, count - 1);
            }
            --rightSize;
            leftHeap.put(minRight, leftHeap.getOrDefault(minRight, 0) + 1);
            ++leftSize;
        }
    }
}
