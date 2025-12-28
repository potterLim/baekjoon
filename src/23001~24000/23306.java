import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();

        System.out.println("? 1");
        System.out.flush();
        int firstBit = scanner.nextInt();

        System.out.println("? " + length);
        System.out.flush();
        int lastBit = scanner.nextInt();

        if (lastBit > firstBit) {
            System.out.println("! 1");
        } else if (lastBit < firstBit) {
            System.out.println("! -1");
        } else {
            System.out.println("! 0");
        }

        System.out.flush();
    }
}
