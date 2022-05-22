import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static public void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int[] firstAlphabetIdx = new int[26];
        Arrays.fill(firstAlphabetIdx, -1);

        String str = keyboard.nextLine();

        for (int i = 0; i < str.length(); i++) {
            if (firstAlphabetIdx[str.charAt(i) - 97] == -1) {
                firstAlphabetIdx[str.charAt(i) - 97] = i;
            }
        }

        for (int i = 0; i < firstAlphabetIdx.length; i++) {
            System.out.print(firstAlphabetIdx[i] + " ");
        }
    }
}
