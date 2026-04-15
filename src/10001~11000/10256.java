import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static final int ALPHABET_COUNT = 4;
    private static final int ROOT = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < testCount; ++i) {
            int dnaLength = scanner.nextInt();
            int markerLength = scanner.nextInt();
            String dna = scanner.next();
            String marker = scanner.next();

            HashSet<String> mutations = new HashSet<String>();

            for (int j = 0; j < markerLength; ++j) {
                for (int k = j; k < markerLength; ++k) {
                    mutations.add(buildMutation(marker, j, k));
                }
            }

            int maxNodeCount = 1 + mutations.size() * markerLength;
            int[][] next = new int[maxNodeCount][ALPHABET_COUNT];
            int[] fail = new int[maxNodeCount];
            int[] outputCount = new int[maxNodeCount];

            int nodeCount = 1;

            for (String pattern : mutations) {
                int node = ROOT;

                for (int j = 0; j < pattern.length(); ++j) {
                    int letterIndex = toIndex(pattern.charAt(j));

                    if (next[node][letterIndex] == 0) {
                        next[node][letterIndex] = nodeCount;
                        ++nodeCount;
                    }

                    node = next[node][letterIndex];
                }

                ++outputCount[node];
            }

            ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

            for (int j = 0; j < ALPHABET_COUNT; ++j) {
                int child = next[ROOT][j];
                if (child != 0) {
                    queue.add(child);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int j = 0; j < ALPHABET_COUNT; ++j) {
                    int child = next[current][j];
                    if (child == 0) {
                        continue;
                    }

                    int failure = fail[current];

                    while (failure != ROOT && next[failure][j] == 0) {
                        failure = fail[failure];
                    }

                    if (next[failure][j] != 0) {
                        failure = next[failure][j];
                    }

                    fail[child] = failure;
                    outputCount[child] += outputCount[failure];
                    queue.add(child);
                }
            }

            long answer = 0;
            int state = ROOT;

            for (int j = 0; j < dnaLength; ++j) {
                int letterIndex = toIndex(dna.charAt(j));

                while (state != ROOT && next[state][letterIndex] == 0) {
                    state = fail[state];
                }

                if (next[state][letterIndex] != 0) {
                    state = next[state][letterIndex];
                }

                answer += outputCount[state];
            }

            out.append(answer);

            if (i + 1 < testCount) {
                out.append('\n');
            }
        }

        System.out.print(out.toString());
        scanner.close();
    }

    private static String buildMutation(String marker, int start, int end) {
        StringBuilder builder = new StringBuilder(marker.length());

        for (int i = 0; i < start; ++i) {
            builder.append(marker.charAt(i));
        }

        for (int i = end; i >= start; --i) {
            builder.append(marker.charAt(i));
        }

        for (int i = end + 1; i < marker.length(); ++i) {
            builder.append(marker.charAt(i));
        }

        return builder.toString();
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
}