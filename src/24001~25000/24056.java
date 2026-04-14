import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static int[] sDiff;
    private static int sPositiveCount;
    private static int sNegativeCount;
    private static int sOtherCount;
    private static long sRankDiffSum;
    private static long sRankSquareDiffSum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] sorted = new int[n];

        for (int i = 1; i <= n; ++i) {
            a[i] = scanner.nextInt();
            sorted[i - 1] = a[i];
        }

        for (int i = 1; i <= n; ++i) {
            b[i] = scanner.nextInt();
        }

        Arrays.sort(sorted);

        HashMap<Integer, Integer> rankMap = new HashMap<Integer, Integer>(n * 2);

        for (int i = 0; i < n; ++i) {
            rankMap.put(sorted[i], i + 1);
        }

        int[] aRank = new int[n + 1];
        int[] bRank = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            aRank[i] = rankMap.get(a[i]);

            Integer mappedRank = rankMap.get(b[i]);

            if (mappedRank == null) {
                System.out.println(0);
                return;
            }

            bRank[i] = mappedRank.intValue();
        }

        boolean[] suffixSame = new boolean[n + 2];
        suffixSame[n + 1] = true;

        for (int i = n; i >= 1; --i) {
            if (a[i] == b[i] && suffixSame[i + 1]) {
                suffixSame[i] = true;
            } else {
                suffixSame[i] = false;
            }
        }

        sDiff = new int[n + 1];

        boolean isPrefixSorted = true;

        for (int i = 1; i <= n; ++i) {
            updateDiff(aRank[i], -1);
            updateDiff(bRank[i], 1);

            if (i >= 2 && b[i - 1] > b[i]) {
                isPrefixSorted = false;
            }

            if (isPrefixSorted == false || suffixSame[i + 1] == false) {
                continue;
            }

            if (sPositiveCount == 0 && sNegativeCount == 0 && sOtherCount == 0) {
                System.out.println(1);
                return;
            }

            if (sPositiveCount == 1 && sNegativeCount == 1 && sOtherCount == 0) {
                long rankDiff = sRankDiffSum;

                if (rankDiff <= 0) {
                    continue;
                }

                if (sRankSquareDiffSum % rankDiff != 0) {
                    continue;
                }

                long rankSum = sRankSquareDiffSum / rankDiff;

                if ((rankSum - rankDiff) % 2L != 0L) {
                    continue;
                }

                long missingRank = (rankSum - rankDiff) / 2L;
                long duplicatedRank = (rankSum + rankDiff) / 2L;

                if (missingRank == aRank[i] && duplicatedRank > missingRank) {
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
    }

    private static void updateDiff(int rank, int delta) {
        int oldValue = sDiff[rank];

        if (oldValue == 1) {
            --sPositiveCount;
        } else if (oldValue == -1) {
            --sNegativeCount;
        } else if (oldValue != 0) {
            --sOtherCount;
        }

        int newValue = oldValue + delta;
        sDiff[rank] = newValue;

        if (newValue == 1) {
            ++sPositiveCount;
        } else if (newValue == -1) {
            ++sNegativeCount;
        } else if (newValue != 0) {
            ++sOtherCount;
        }

        sRankDiffSum += (long) delta * rank;
        sRankSquareDiffSum += (long) delta * rank * rank;
    }
}