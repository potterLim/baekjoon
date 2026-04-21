import java.util.Scanner;

public class Main {
    private static int sMaxLength;
    private static int sStateCount;
    private static boolean[][] sCombineComputed;
    private static long[][] sCombineLowMasks;
    private static long[][] sCombineHighMasks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        scanner.close();

        int length = input.length();

        sMaxLength = length;
        sStateCount = 2 * (sMaxLength + 1);
        sCombineComputed = new boolean[sStateCount][sStateCount];
        sCombineLowMasks = new long[sStateCount][sStateCount];
        sCombineHighMasks = new long[sStateCount][sStateCount];

        int[][][] states = new int[length][length][sStateCount];
        int[][] stateCounts = new int[length][length];

        for (int i = 0; i < length; ++i) {
            int bit = input.charAt(i) - '0';
            int stateId = getStateId(bit, 1);

            states[i][i][0] = stateId;
            stateCounts[i][i] = 1;
        }

        for (int substringLength = 2; substringLength <= length; ++substringLength) {
            for (int i = 0; i + substringLength - 1 < length; ++i) {
                int j = i + substringLength - 1;

                long lowMask = 0L;
                long highMask = 0L;

                for (int k = i; k < j; ++k) {
                    int leftCount = stateCounts[i][k];
                    int rightCount = stateCounts[k + 1][j];

                    for (int a = 0; a < leftCount; ++a) {
                        int leftStateId = states[i][k][a];

                        for (int b = 0; b < rightCount; ++b) {
                            int rightStateId = states[k + 1][j][b];

                            computeCombineMask(leftStateId, rightStateId);

                            lowMask |= sCombineLowMasks[leftStateId][rightStateId];
                            highMask |= sCombineHighMasks[leftStateId][rightStateId];
                        }
                    }
                }

                int count = 0;

                for (int stateId = 1; stateId < sStateCount; ++stateId) {
                    if (getStateLength(stateId) == 0) {
                        continue;
                    }

                    if (containsState(lowMask, highMask, stateId)) {
                        states[i][j][count] = stateId;
                        ++count;
                    }
                }

                stateCounts[i][j] = count;
            }
        }

        int answer = Integer.MAX_VALUE;
        int finalCount = stateCounts[0][length - 1];

        for (int i = 0; i < finalCount; ++i) {
            int stateId = states[0][length - 1][i];
            int resultLength = getStateLength(stateId);

            if (resultLength < answer) {
                answer = resultLength;
            }
        }

        System.out.print(answer);
    }

    private static int getStateId(int startBit, int length) {
        return startBit * (sMaxLength + 1) + length;
    }

    private static int getStateStartBit(int stateId) {
        return stateId / (sMaxLength + 1);
    }

    private static int getStateLength(int stateId) {
        return stateId % (sMaxLength + 1);
    }

    private static boolean containsState(long lowMask, long highMask, int stateId) {
        if (stateId < 64) {
            return (lowMask & (1L << stateId)) != 0L;
        }

        return (highMask & (1L << (stateId - 64))) != 0L;
    }

    private static void addStateToMask(long[] mask, int stateId) {
        if (stateId < 64) {
            mask[0] |= 1L << stateId;
            return;
        }

        mask[1] |= 1L << (stateId - 64);
    }

    private static void computeCombineMask(int leftStateId, int rightStateId) {
        if (sCombineComputed[leftStateId][rightStateId]) {
            return;
        }

        sCombineComputed[leftStateId][rightStateId] = true;

        int leftStartBit = getStateStartBit(leftStateId);
        int leftLength = getStateLength(leftStateId);
        int rightStartBit = getStateStartBit(rightStateId);
        int rightLength = getStateLength(rightStateId);

        int leftLastBit = leftStartBit ^ ((leftLength - 1) & 1);

        long[] mask = new long[2];

        if (leftLastBit != rightStartBit) {
            int mergedStateId = getStateId(leftStartBit, leftLength + rightLength);
            addStateToMask(mask, mergedStateId);
        } else {
            int maxDeleteLength = Math.min(leftLength, rightLength);

            for (int deleteLength = 1; deleteLength <= maxDeleteLength; ++deleteLength) {
                if (deleteLength == rightLength) {
                    addStateToMask(mask, leftStateId);
                    continue;
                }

                int nextRightStartBit = rightStartBit ^ (deleteLength & 1);
                int nextRightLength = rightLength - deleteLength;
                int nextRightStateId = getStateId(nextRightStartBit, nextRightLength);

                computeCombineMask(leftStateId, nextRightStateId);

                mask[0] |= sCombineLowMasks[leftStateId][nextRightStateId];
                mask[1] |= sCombineHighMasks[leftStateId][nextRightStateId];
            }
        }

        sCombineLowMasks[leftStateId][rightStateId] = mask[0];
        sCombineHighMasks[leftStateId][rightStateId] = mask[1];
    }
}