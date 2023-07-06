import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        HashMap<Integer, Integer> card = new HashMap<Integer, Integer>();
        StringBuilder sb = new StringBuilder();

        int N = keyboard.nextInt();
        int num;
        for(int i = 0; i < N; i++) {
            num = keyboard.nextInt();
            if(card.containsKey(num)) {
                card.put(num, card.get(num)+1);
            } else {
                card.put(num, 1);
            }
        }

        int M = keyboard.nextInt();
        for(int i = 0; i < M; i++) {
            num = keyboard.nextInt();
            if(card.containsKey(num)) {
                sb.append(card.get(num)).append(" ");
            } else {
                sb.append("0 ");
            }
        }

        System.out.println(sb);
    }
}