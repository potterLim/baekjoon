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

        int variableCount = scanner.nextInt();
        int clauseCount = scanner.nextInt();

        int nodeCount = variableCount * 2;
        adjacentLists = new ArrayList<>();
        for (int i = 0; i <= nodeCount; i++) {
            adjacentLists.add(new ArrayList<>());
        }

        for (int i = 0; i < clauseCount; ++i) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            int notU;
            int actualU;
            if (u > 0) {
                notU = Math.abs(u) * 2;
                actualU = Math.abs(u) * 2 - 1;
            } else {
                notU = Math.abs(u) * 2 - 1;
                actualU = Math.abs(u) * 2;
            }

            int notV;
            int actualV;
            if (v > 0) {
                notV = Math.abs(v) * 2;
                actualV = Math.abs(v) * 2 - 1;
            } else {
                notV = Math.abs(v) * 2 - 1;
                actualV = Math.abs(v) * 2;
            }

            adjacentLists.get(notU).add(actualV);
            adjacentLists.get(notV).add(actualU);
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

        int[] results = new int[variableCount + 1];
        for (int i = 1; i <= variableCount; ++i) {
            if (sccIds[i * 2 - 1] == sccIds[i * 2]) {
                System.out.println(0);
                return;
            }

            if (sccIds[i * 2 - 1] < sccIds[i * 2]) {
                results[i] = 1;
            } else {
                results[i] = 0;
            }
        }

        System.out.println(1);
        for (int i = 1; i <= variableCount; ++i) {
            System.out.print(results[i] + " ");
        }
    }

    private static int findSccRecursive(int currentNode) {
        ++visitCounter;
        discoveredOrder[currentNode] = visitCounter;
        stack.push(currentNode);

        int result = discoveredOrder[currentNode];
        for (int neighbor : adjacentLists.get(currentNode)) {
            if (discoveredOrder[neighbor] == 0) {
                int recursiveResult = findSccRecursive(neighbor);
                result = Math.min(result, recursiveResult);
            } else if (!isFinished[neighbor]) {
                result = Math.min(result, discoveredOrder[neighbor]);
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