import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String binary = Integer.toBinaryString(number);
        String reversedBinary = new StringBuilder(binary).reverse().toString();
        int reversedDecimal = Integer.parseInt(reversedBinary, 2);
        System.out.println(reversedDecimal);

        scanner.close();
    }
}