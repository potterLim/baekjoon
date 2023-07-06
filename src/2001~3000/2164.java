import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int count = keyboard.nextInt();
        Queue<Integer> card = new LinkedList<Integer>();

        for (int i = 0; i <count; ++i) {
            card.add(i + 1);
        }

        while(true) {
            if (card.size() == 1) {
                System.out.println(card.poll());
                break;
            }
            card.poll();
            card.add(card.poll());
        }
    }
}