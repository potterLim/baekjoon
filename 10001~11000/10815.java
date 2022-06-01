import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean[] card = new boolean[20000001];

        int n = keyboard.nextInt();
        for (int i = 0; i < n; ++i) {
            int num = keyboard.nextInt();
            num += 10000000;
            card[num] = true;
        }

        n = keyboard.nextInt();
        for (int i = 0; i < n; ++i) {
            int num = keyboard.nextInt();
            num += 10000000;
            if(card[num] == true) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
    }
}