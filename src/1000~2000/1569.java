import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static boolean canCover(int[] xs, int[] ys, int n, int left, int bottom, int side) {
        int right = left + side;
        int top = bottom + side;

        for (int i = 0; i < n; ++i) {
            int x = xs[i];
            int y = ys[i];

            if (x < left || x > right || y < bottom || y > top) {
                return false;
            }

            if (x != left && x != right && y != bottom && y != top) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] xs = new int[n];
        int[] ys = new int[n];

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            xs[i] = x;
            ys[i] = y;

            if (x < minX) {
                minX = x;
            }

            if (x > maxX) {
                maxX = x;
            }

            if (y < minY) {
                minY = y;
            }

            if (y > maxY) {
                maxY = y;
            }
        }

        int side = Math.max(maxX - minX, maxY - minY);

        HashSet<Integer> leftCandidates = new HashSet<Integer>();
        HashSet<Integer> bottomCandidates = new HashSet<Integer>();

        leftCandidates.add(minX);
        leftCandidates.add(maxX - side);

        bottomCandidates.add(minY);
        bottomCandidates.add(maxY - side);

        for (int i = 0; i < n; ++i) {
            leftCandidates.add(xs[i]);
            leftCandidates.add(xs[i] - side);

            bottomCandidates.add(ys[i]);
            bottomCandidates.add(ys[i] - side);
        }

        for (int left : leftCandidates) {
            for (int bottom : bottomCandidates) {
                if (canCover(xs, ys, n, left, bottom, side)) {
                    System.out.println(side);
                    scanner.close();
                    return;
                }
            }
        }

        System.out.println(-1);
        scanner.close();
    }
}