import java.util.Scanner;

public class Main {
    private static final int ALPHABET_COUNT = 4;
    private static final int MAX_N = 1000;
    private static final int MAX_STATE_COUNT = MAX_N * 2 + 5;
    private static final int NONE = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int minOccurrence = scanner.nextInt();
        int k = scanner.nextInt();
        String dna = scanner.next();

        int[][] next = new int[MAX_STATE_COUNT][ALPHABET_COUNT];
        int[] link = new int[MAX_STATE_COUNT];
        int[] length = new int[MAX_STATE_COUNT];
        int[] occurrence = new int[MAX_STATE_COUNT];

        for (int i = 0; i < MAX_STATE_COUNT; ++i) {
            link[i] = NONE;
            for (int j = 0; j < ALPHABET_COUNT; ++j) {
                next[i][j] = NONE;
            }
        }

        int stateCount = 1;
        int last = 0;

        for (int i = 0; i < dna.length(); ++i) {
            int letter = toIndex(dna.charAt(i));

            int current = stateCount;
            ++stateCount;

            length[current] = length[last] + 1;
            occurrence[current] = 1;

            int p = last;
            while (p != NONE && next[p][letter] == NONE) {
                next[p][letter] = current;
                p = link[p];
            }

            if (p == NONE) {
                link[current] = 0;
            } else {
                int q = next[p][letter];

                if (length[p] + 1 == length[q]) {
                    link[current] = q;
                } else {
                    int clone = stateCount;
                    ++stateCount;

                    length[clone] = length[p] + 1;
                    link[clone] = link[q];

                    for (int j = 0; j < ALPHABET_COUNT; ++j) {
                        next[clone][j] = next[q][j];
                    }

                    while (p != NONE && next[p][letter] == q) {
                        next[p][letter] = clone;
                        p = link[p];
                    }

                    link[q] = clone;
                    link[current] = clone;
                }
            }

            last = current;
        }

        int[] countByLength = new int[n + 1];
        for (int i = 0; i < stateCount; ++i) {
            ++countByLength[length[i]];
        }

        for (int i = 1; i <= n; ++i) {
            countByLength[i] += countByLength[i - 1];
        }

        int[] order = new int[stateCount];
        for (int i = stateCount - 1; i >= 0; --i) {
            order[--countByLength[length[i]]] = i;
        }

        for (int i = stateCount - 1; i > 0; --i) {
            int state = order[i];
            int parent = link[state];

            if (parent != NONE) {
                occurrence[parent] += occurrence[state];
            }
        }

        int[][] dp = new int[stateCount][n + 1];

        for (int state = 0; state < stateCount; ++state) {
            if (occurrence[state] >= minOccurrence) {
                dp[state][0] = 1;
            }
        }

        for (int remain = 1; remain <= n; ++remain) {
            for (int i = stateCount - 1; i >= 0; --i) {
                int state = order[i];
                int count = 0;

                for (int letter = 0; letter < ALPHABET_COUNT; ++letter) {
                    int nextState = next[state][letter];
                    if (nextState != NONE) {
                        count += dp[nextState][remain - 1];
                    }
                }

                dp[state][remain] = count;
            }
        }

        int totalCount = 0;
        for (int len = 1; len <= n; ++len) {
            totalCount += dp[0][len];
        }

        System.out.println(totalCount);

        int targetLength = 0;
        for (int len = 1; len <= n; ++len) {
            if (k > dp[0][len]) {
                k -= dp[0][len];
            } else {
                targetLength = len;
                break;
            }
        }

        StringBuilder answer = new StringBuilder();
        int currentState = 0;
        int remain = targetLength;

        while (remain > 0) {
            for (int letter = 0; letter < ALPHABET_COUNT; ++letter) {
                int nextState = next[currentState][letter];
                if (nextState == NONE) {
                    continue;
                }

                int count = dp[nextState][remain - 1];
                if (k > count) {
                    k -= count;
                } else {
                    answer.append(toChar(letter));
                    currentState = nextState;
                    --remain;
                    break;
                }
            }
        }

        System.out.println(answer.toString());
        scanner.close();
    }

    private static int toIndex(char c) {
        if (c == 'A') {
            return 0;
        }

        if (c == 'C') {
            return 1;
        }

        if (c == 'G') {
            return 2;
        }

        return 3;
    }

    private static char toChar(int index) {
        if (index == 0) {
            return 'A';
        }

        if (index == 1) {
            return 'C';
        }

        if (index == 2) {
            return 'G';
        }

        return 'T';
    }
}