import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String testCaseLine = reader.readLine();
        if (testCaseLine == null) {
            return;
        }

        int testCaseCount = Integer.parseInt(testCaseLine);

        for (int t = 0; t < testCaseCount; ++t) {
            String islandCountLine = reader.readLine();
            if (islandCountLine == null) {
                break;
            }

            int islandCount = Integer.parseInt(islandCountLine);
            int[][] islands = new int[islandCount][2];
            int[] yCoordinates = new int[islandCount];

            for (int i = 0; i < islandCount; ++i) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                islands[i][0] = Integer.parseInt(tokenizer.nextToken());
                islands[i][1] = Integer.parseInt(tokenizer.nextToken());
                yCoordinates[i] = islands[i][1];
            }

            Arrays.sort(yCoordinates);
            int uniqueYCount = 0;
            if (islandCount > 0) {
                uniqueYCount = 1;
                for (int i = 1; i < islandCount; ++i) {
                    if (yCoordinates[i] != yCoordinates[i - 1]) {
                        yCoordinates[uniqueYCount] = yCoordinates[i];
                        ++uniqueYCount;
                    }
                }
            }

            Arrays.sort(islands, new Comparator<int[]>() {
                @Override
                public int compare(int[] firstIsland, int[] secondIsland) {
                    if (firstIsland[0] == secondIsland[0]) {
                        return Integer.compare(secondIsland[1], firstIsland[1]);
                    }
                    return Integer.compare(firstIsland[0], secondIsland[0]);
                }
            });

            long pairCount = 0;
            long[] tree = new long[uniqueYCount + 1];

            for (int i = 0; i < islandCount; ++i) {
                int compressedY = findCompressedIndex(yCoordinates, uniqueYCount, islands[i][1]);

                pairCount += query(tree, uniqueYCount) - query(tree, compressedY - 1);

                update(tree, uniqueYCount, compressedY, 1);
            }

            System.out.println(pairCount);
        }
    }

    private static int findCompressedIndex(int[] yCoordinates, int size, int targetY) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (yCoordinates[mid] == targetY) {
                return mid + 1;
            }

            if (yCoordinates[mid] < targetY) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + 1;
    }

    private static void update(long[] tree, int size, int index, int value) {
        while (index <= size) {
            tree[index] += value;
            index += (index & -index);
        }
    }

    private static long query(long[] tree, int index) {
        long sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= (index & -index);
        }
        return sum;
    }
}