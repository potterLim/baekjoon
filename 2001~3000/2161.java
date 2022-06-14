import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = keyboard.nextInt();
        ArrayList<Integer> cards = new ArrayList<>(N);

        for(int i = 1; i <=N; i++) {
            cards.add(i);
        }

        while(true) {
            if(cards.size() ==1) {
                break;
            }
            sb.append(cards.get(0)).append(" ");
            cards.remove(0);
            cards.add(cards.get(0));
            cards.remove(0);
        }

        sb.append(cards.get(0));
        System.out.print(sb);
    }
}
