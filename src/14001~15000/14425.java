import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String str = keyboard.nextLine();
        int N = Integer.parseInt(str.split(" ")[0]);
        int M = Integer.parseInt(str.split(" ")[1]);
        ArrayList<String> S = new ArrayList<String>();

        for (int i = 0; i < N; i++) {
            S.add(keyboard.nextLine());
        }

        int count = 0;

        for (int i = 0; i < M; i++) {
            if(S.contains(keyboard.nextLine()) == true) {
                count++;
            }
        }

        System.out.println(count);
    }
}