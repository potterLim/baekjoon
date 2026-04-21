import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static final StringBuilder sBuilder = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int diskCount = scanner.nextInt();
        scanner.close();

        BigInteger moveCount = BigInteger.ONE.shiftLeft(diskCount).subtract(BigInteger.ONE);
        sBuilder.append(moveCount).append('\n');

        if (diskCount <= 20) {
            move(diskCount, 1, 2, 3);
        }

        System.out.print(sBuilder.toString());
    }

    private static void move(int diskCount, int from, int by, int to) {
        if (diskCount == 1) {
            sBuilder.append(from).append(' ').append(to).append('\n');
            return;
        }

        move(diskCount - 1, from, to, by);
        sBuilder.append(from).append(' ').append(to).append('\n');
        move(diskCount - 1, by, from, to);
    }
}