import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String num_str = keyboard.nextLine();

        ArrayList<Integer> num = new ArrayList<Integer>();

        for (int i = 0; i < num_str.length(); i++) {
            num.add(num_str.charAt(i) - '0');
        }
        Collections.sort(num);
        for (int i = num.size() - 1; i >= 0; i--) {
            System.out.print(num.get(i));
        }
    }
}
