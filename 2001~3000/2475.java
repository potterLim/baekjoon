import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int[] unique_number = new int[5];

        for (int i = 0; i < 5; i++) {
            unique_number[i] = keyboard.nextInt();
        }

        int valided_number = 0;

        for (int i = 0; i < 5; i++) {
            valided_number += unique_number[i] * unique_number[i];
        }

        valided_number = valided_number % 10;

        System.out.println(valided_number);
    }
}
