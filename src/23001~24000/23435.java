import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final class Node {
        private final int index;
        private final ArrayList<Integer> defeated;

        private Node(int index) {
            this.index = index;
            this.defeated = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        Node[] currentRound = new Node[size];
        for (int i = 0; i < size; i++) {
            currentRound[i] = new Node(i);
        }

        int currentSize = size;

        while (currentSize > 1) {
            int nextSize = (currentSize + 1) / 2;
            Node[] nextRound = new Node[nextSize];

            int nextIndex = 0;

            for (int i = 0; i + 1 < currentSize; i += 2) {
                Node left = currentRound[i];
                Node right = currentRound[i + 1];

                int winnerIndex = getWinnerIndex(scanner, left.index, right.index);

                if (winnerIndex == left.index) {
                    left.defeated.add(right.index);
                    nextRound[nextIndex] = left;
                } else {
                    right.defeated.add(left.index);
                    nextRound[nextIndex] = right;
                }

                nextIndex++;
            }

            if (currentSize % 2 == 1) {
                nextRound[nextIndex] = currentRound[currentSize - 1];
            }

            currentRound = nextRound;
            currentSize = nextSize;
        }

        Node minimumNode = currentRound[0];

        ArrayList<Integer> candidates = minimumNode.defeated;

        int secondMinimumIndex = candidates.get(0);
        for (int i = 1; i < candidates.size(); i++) {
            int candidateIndex = candidates.get(i);

            int winnerIndex = getWinnerIndex(scanner, secondMinimumIndex, candidateIndex);
            if (winnerIndex == candidateIndex) {
                secondMinimumIndex = candidateIndex;
            }
        }

        System.out.println("! " + secondMinimumIndex);
        System.out.flush();
    }

    private static int getWinnerIndex(Scanner scanner, int leftIndex, int rightIndex) {
        System.out.println("? " + leftIndex + " " + rightIndex);
        System.out.flush();

        String response = scanner.next();
        if (response.charAt(0) == '<') {
            return leftIndex;
        }

        return rightIndex;
    }
}
