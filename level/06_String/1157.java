import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] countAlphabet = new int[26];
        Scanner keyboard = new Scanner(System.in);

        String str = keyboard.next();
        str = str.toUpperCase();

        for (int i = 0; i < str.length(); i++) {
            countAlphabet[str.charAt(i) - 65]++;
        }

        char countMaxChar = 0;
        int countMax = 0;
        for (int i = 0; i < countAlphabet.length; i++) {
            if (countAlphabet[i] > countMax) {
                countMaxChar = (char) (i + 65);
                countMax = countAlphabet[i];
            } else if (countAlphabet[i] == countMax) {
                countMaxChar = '?';
            }
        }

        System.out.println(countMaxChar);

    }
}
