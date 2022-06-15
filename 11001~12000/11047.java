import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int count_type_coin = keyboard.nextInt();
        int price = keyboard.nextInt();
        ArrayList<Integer> type_coins = new ArrayList<Integer>(count_type_coin);

        int type_coin;
        for(int i = 0; i < count_type_coin; i++) {
            type_coin = keyboard.nextInt();
            type_coins.add(type_coin);
        }

        int count_total_coin = 0;
        for (int i = count_type_coin - 1; i >=0; i--) {
            if (price == 0) {
                break;
            }

            count_total_coin += price / type_coins.get(i);
            price = price % type_coins.get(i);
        }

        System.out.println(count_total_coin);

    }
}
