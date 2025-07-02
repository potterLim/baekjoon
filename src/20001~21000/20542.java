import java.util.Scanner;

public class Main {
    private static boolean isSameLetter(char answerLetter, char correctLetter) {
        if (answerLetter == correctLetter) {
            return true;
        }
        if (answerLetter == 'i' && (correctLetter == 'i' || correctLetter == 'j' || correctLetter == 'l')) {
            return true;
        }
        if (answerLetter == 'v' && (correctLetter == 'v' || correctLetter == 'w')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int answerLength = scanner.nextInt();
        int correctLength = scanner.nextInt();
        scanner.nextLine();
        String answerString = scanner.nextLine();
        String correctString = scanner.nextLine();
        scanner.close();

        int[] previousRow = new int[correctLength + 1];
        int[] currentRow = new int[correctLength + 1];

        for (int j = 0; j <= correctLength; ++j) {
            previousRow[j] = j;
        }

        for (int i = 1; i <= answerLength; ++i) {
            currentRow[0] = i;
            char answerChar = answerString.charAt(i - 1);

            for (int j = 1; j <= correctLength; ++j) {
                char correctChar = correctString.charAt(j - 1);
                if (isSameLetter(answerChar, correctChar)) {
                    currentRow[j] = previousRow[j - 1];
                } else {
                    int insertCost = currentRow[j - 1];
                    int deleteCost = previousRow[j];
                    int replaceCost = previousRow[j - 1];
                    currentRow[j] = Math.min(Math.min(insertCost, deleteCost), replaceCost) + 1;
                }
            }

            int[] temp = previousRow;
            previousRow = currentRow;
            currentRow = temp;
        }

        System.out.println(previousRow[correctLength]);
    }
}