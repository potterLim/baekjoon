import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String word = keyboard.next();

        int countAlphabets = 0;
        for (int i = 0; i < word.length(); i++) {
            countAlphabets++;
            if (word.charAt(i) == 'c' && i != word.length() - 1) {
                if (word.charAt(i + 1) == '=') {
                    i++;
                } else if (word.charAt(i + 1) == '-') {
                    i++;
                }
            } else if (word.charAt(i) == 'd' && i != word.length() - 1) {
                if (i != word.length() - 2 && word.charAt(i + 1) == 'z' && word.charAt(i + 2) == '=') {
                    i += 2;
                } else if (word.charAt(i + 1) == '-') {
                    i++;
                }
            } else if (word.charAt(i) == 'l' && i != word.length() - 1) {
                if (word.charAt(i + 1) == 'j') {
                    i++;
                }
            } else if (word.charAt(i) == 'n' && i != word.length() - 1) {
                if (word.charAt(i + 1) == 'j') {
                    i++;
                }
            } else if (word.charAt(i) == 's' && i != word.length() - 1) {
                if (word.charAt(i + 1) == '=') {
                    i++;
                }
            } else if (word.charAt(i) == 'z' && i != word.length() - 1) {
                if (word.charAt(i + 1) == '=') {
                    i++;
                }
            }


        }

        System.out.println(countAlphabets);
    }
}
