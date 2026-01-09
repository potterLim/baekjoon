import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class TrieNode {
        int[] next;
        int fail;
        boolean isEnd;

        TrieNode() {
            next = new int[26];
            fail = 0;
            isEnd = false;
        }
    }

    static ArrayList<TrieNode> trie = new ArrayList<>();

    static void insertPattern(String pattern) {
        int currentNode = 0;

        for (int i = 0; i < pattern.length(); i++) {
            int index = pattern.charAt(i) - 'a';

            if (trie.get(currentNode).next[index] == 0) {
                trie.add(new TrieNode());
                trie.get(currentNode).next[index] = trie.size() - 1;
            }

            currentNode = trie.get(currentNode).next[index];
        }

        trie.get(currentNode).isEnd = true;
    }

    static void buildFailLinks() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < 26; i++) {
            int nextNode = trie.get(0).next[i];

            if (nextNode != 0) {
                queue.add(nextNode);
                trie.get(nextNode).fail = 0;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < 26; i++) {
                int next = trie.get(current).next[i];

                if (next != 0) {
                    int failNode = trie.get(current).fail;

                    while (failNode != 0 && trie.get(failNode).next[i] == 0) {
                        failNode = trie.get(failNode).fail;
                    }

                    if (trie.get(failNode).next[i] != 0) {
                        failNode = trie.get(failNode).next[i];
                    }

                    trie.get(next).fail = failNode;

                    if (trie.get(failNode).isEnd) {
                        trie.get(next).isEnd = true;
                    }

                    queue.add(next);
                }
            }
        }
    }

    static boolean checkString(String text) {
        int currentNode = 0;

        for (int i = 0; i < text.length(); i++) {
            int index = text.charAt(i) - 'a';

            while (currentNode != 0 && trie.get(currentNode).next[index] == 0) {
                currentNode = trie.get(currentNode).fail;
            }

            if (trie.get(currentNode).next[index] != 0) {
                currentNode = trie.get(currentNode).next[index];
            }

            if (trie.get(currentNode).isEnd) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        trie.add(new TrieNode());

        int patternCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < patternCount; i++) {
            insertPattern(reader.readLine());
        }

        buildFailLinks();

        int queryCount = Integer.parseInt(reader.readLine());

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < queryCount; i++) {
            String query = reader.readLine();

            if (checkString(query)) {
                result.append("YES\n");
            } else {
                result.append("NO\n");
            }
        }

        System.out.print(result.toString());
    }
}