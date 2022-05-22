import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean[] existAlphabet = new boolean[26];
        Scanner keyboard = new Scanner(System.in);
        int num = keyboard.nextInt();
        int count = 0;

        inputNewLine:
        for (int i = 0; i < num; i++) {
            String str = keyboard.next();
            Arrays.fill(existAlphabet, false);
            existAlphabet[str.charAt(0) - 'a'] = true;

            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) != str.charAt(j - 1)) {
                    if (existAlphabet[str.charAt(j) - 'a'] == true) {
                        continue inputNewLine;
                    }

                    existAlphabet[str.charAt(j) - 'a'] = true;
                }
            }
            count++;
        }

        System.out.println(count);
    }
}
