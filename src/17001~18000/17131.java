import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int starCount = scanner.nextInt();
        Star[] stars = new Star[starCount];
        int[] yCoordinates = new int[starCount];

        for (int i = 0; i < starCount; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            stars[i] = new Star(x, y);
            yCoordinates[i] = y;
        }

        Arrays.sort(yCoordinates);
        ArrayList<Integer> distinctYList = new ArrayList<>();
        if (starCount > 0) {
            distinctYList.add(yCoordinates[0]);
            for (int i = 1; i < starCount; ++i) {
                if (yCoordinates[i] != yCoordinates[i - 1]) {
                    distinctYList.add(yCoordinates[i]);
                }
            }
        }

        HashMap<Integer, Integer> yRankMap = new HashMap<>();
        for (int i = 0; i < distinctYList.size(); ++i) {
            yRankMap.put(distinctYList.get(i), i + 1);
        }

        Arrays.sort(stars, (a, b) -> {
            if (a.x != b.x) {
                return Integer.compare(a.x, b.x);
            }
            return Integer.compare(a.y, b.y);
        });

        int maxRank = distinctYList.size();
        FenwickTree leftTree = new FenwickTree(maxRank);
        FenwickTree rightTree = new FenwickTree(maxRank);

        for (int i = 0; i < starCount; ++i) {
            rightTree.update(yRankMap.get(stars[i].y), 1);
        }

        long totalVCount = 0;
        long divisor = 1000000007;

        int currentIndex = 0;
        while (currentIndex < starCount) {
            int groupEndIndex = currentIndex;
            while (groupEndIndex < starCount && stars[groupEndIndex].x == stars[currentIndex].x) {
                rightTree.update(yRankMap.get(stars[groupEndIndex].y), -1);
                groupEndIndex++;
            }

            for (int i = currentIndex; i < groupEndIndex; ++i) {
                int rank = yRankMap.get(stars[i].y);
                long leftHigherCount = leftTree.query(maxRank) - leftTree.query(rank);
                long rightHigherCount = rightTree.query(maxRank) - rightTree.query(rank);

                totalVCount = (totalVCount + (leftHigherCount * rightHigherCount)) % divisor;
            }

            for (int i = currentIndex; i < groupEndIndex; ++i) {
                leftTree.update(yRankMap.get(stars[i].y), 1);
            }

            currentIndex = groupEndIndex;
        }

        System.out.println(totalVCount);
        scanner.close();
    }

    private static class Star {
        public int x;
        public int y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
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