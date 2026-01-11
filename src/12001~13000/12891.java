import java.util.Scanner;

public class Main {
    static int[] minRequired = new int[4];
    static int[] currentCounts = new int[4];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sLength = scanner.nextInt();
        int pLength = scanner.nextInt();
        String dna = scanner.next();

        for (int i = 0; i < 4; ++i) {
            minRequired[i] = scanner.nextInt();
        }

        int result = 0;
        char[] dnaChars = dna.toCharArray();

        for (int i = 0; i < pLength; ++i) {
            addChar(dnaChars[i]);
        }

        if (checkCondition()) {
            ++result;
        }

        for (int i = pLength; i < sLength; ++i) {
            int j = i - pLength;

            addChar(dnaChars[i]);
            removeChar(dnaChars[j]);

            if (checkCondition()) {
                ++result;
            }
        }

        System.out.println(result);
        scanner.close();
    }

    private static void addChar(char c) {
        if (c == 'A') ++currentCounts[0];
        else if (c == 'C') ++currentCounts[1];
        else if (c == 'G') ++currentCounts[2];
        else if (c == 'T') ++currentCounts[3];
    }

    private static void removeChar(char c) {
        if (c == 'A') --currentCounts[0];
        else if (c == 'C') --currentCounts[1];
        else if (c == 'G') --currentCounts[2];
        else if (c == 'T') --currentCounts[3];
    }

    private static boolean checkCondition() {
        for (int i = 0; i < 4; ++i) {
            if (currentCounts[i] < minRequired[i]) {
                return false;
            }
        }
        return true;
    }
}