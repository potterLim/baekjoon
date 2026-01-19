import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static int visitCounter;
    private static int sccCounter;
    private static int[] discoveredOrder;
    private static int[] sccIds;
    private static boolean[] isFinished;
    private static ArrayList<ArrayList<Integer>> adjacentLists;
    private static Stack<Integer> stack;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int participantCount = scanner.nextInt();
            int judgeCount = scanner.nextInt();

            int nodeCount = participantCount * 2;
            adjacentLists = new ArrayList<>();
            for (int i = 0; i <= nodeCount; ++i) {
                adjacentLists.add(new ArrayList<>());
            }

            for (int i = 0; i < judgeCount; ++i) {
                int firstVote = scanner.nextInt();
                int secondVote = scanner.nextInt();

                int firstFalseNode;
                int firstTrueNode;
                if (firstVote > 0) {
                    firstFalseNode = Math.abs(firstVote) * 2;
                    firstTrueNode = Math.abs(firstVote) * 2 - 1;
                } else {
                    firstFalseNode = Math.abs(firstVote) * 2 - 1;
                    firstTrueNode = Math.abs(firstVote) * 2;
                }

                int secondFalseNode;
                int secondTrueNode;
                if (secondVote > 0) {
                    secondFalseNode = Math.abs(secondVote) * 2;
                    secondTrueNode = Math.abs(secondVote) * 2 - 1;
                } else {
                    secondFalseNode = Math.abs(secondVote) * 2 - 1;
                    secondTrueNode = Math.abs(secondVote) * 2;
                }

                adjacentLists.get(firstFalseNode).add(secondTrueNode);
                adjacentLists.get(secondFalseNode).add(firstTrueNode);
            }

            int sanggeunTrueNode = 1;
            int sanggeunFalseNode = 2;
            adjacentLists.get(sanggeunFalseNode).add(sanggeunTrueNode);

            discoveredOrder = new int[nodeCount + 1];
            sccIds = new int[nodeCount + 1];
            isFinished = new boolean[nodeCount + 1];
            stack = new Stack<>();
            visitCounter = 0;
            sccCounter = 0;

            for (int i = 1; i <= nodeCount; ++i) {
                if (discoveredOrder[i] == 0) {
                    findSccRecursive(i);
                }
            }

            boolean isPossible = true;
            for (int i = 1; i <= participantCount; ++i) {
                if (sccIds[i * 2 - 1] == sccIds[i * 2]) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    private static int findSccRecursive(int currentNode) {
        ++visitCounter;
        discoveredOrder[currentNode] = visitCounter;
        stack.push(currentNode);

        int result = discoveredOrder[currentNode];
        for (int neighborNode : adjacentLists.get(currentNode)) {
            if (discoveredOrder[neighborNode] == 0) {
                int recursiveResult = findSccRecursive(neighborNode);
                result = Math.min(result, recursiveResult);
            } else if (!isFinished[neighborNode]) {
                result = Math.min(result, discoveredOrder[neighborNode]);
            }
        }

        if (result == discoveredOrder[currentNode]) {
            ++sccCounter;
            while (true) {
                int topNode = stack.pop();
                sccIds[topNode] = sccCounter;
                isFinished[topNode] = true;
                if (topNode == currentNode) {
                    break;
                }
            }
        }

        return result;
    }
}