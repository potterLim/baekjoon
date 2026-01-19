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

        int lampCount = scanner.nextInt();
        int participantCount = scanner.nextInt();

        int nodeCount = lampCount * 2;
        adjacentLists = new ArrayList<>();
        for (int i = 0; i <= nodeCount; ++i) {
            adjacentLists.add(new ArrayList<>());
        }

        for (int i = 0; i < participantCount; ++i) {
            int[] lampNumbers = new int[3];
            int[] isRed = new int[3];

            for (int j = 0; j < 3; ++j) {
                lampNumbers[j] = scanner.nextInt();
                String color = scanner.next();
                if (color.equals("R")) {
                    isRed[j] = 1;
                } else {
                    isRed[j] = 0;
                }
            }

            for (int j = 0; j < 3; ++j) {
                int k = (j + 1) % 3;

                int firstLamp = lampNumbers[j];
                int firstIsRed = isRed[j];
                int secondLamp = lampNumbers[k];
                int secondIsRed = isRed[k];

                int firstTrueNode;
                int firstFalseNode;
                if (firstIsRed == 1) {
                    firstTrueNode = firstLamp * 2 - 1;
                    firstFalseNode = firstLamp * 2;
                } else {
                    firstTrueNode = firstLamp * 2;
                    firstFalseNode = firstLamp * 2 - 1;
                }

                int secondTrueNode;
                int secondFalseNode;
                if (secondIsRed == 1) {
                    secondTrueNode = secondLamp * 2 - 1;
                    secondFalseNode = secondLamp * 2;
                } else {
                    secondTrueNode = secondLamp * 2;
                    secondFalseNode = secondLamp * 2 - 1;
                }

                adjacentLists.get(firstFalseNode).add(secondTrueNode);
                adjacentLists.get(secondFalseNode).add(firstTrueNode);
            }
        }

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

        char[] results = new char[lampCount + 1];
        for (int i = 1; i <= lampCount; ++i) {
            int redNode = i * 2 - 1;
            int blueNode = i * 2;

            if (sccIds[redNode] == sccIds[blueNode]) {
                System.out.println(-1);
                return;
            }

            if (sccIds[redNode] < sccIds[blueNode]) {
                results[i] = 'R';
            } else {
                results[i] = 'B';
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= lampCount; ++i) {
            output.append(results[i]);
        }
        System.out.println(output.toString());
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