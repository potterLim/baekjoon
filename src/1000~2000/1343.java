import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String board = scanner.next();

        board = board.replace("XXXX", "AAAA");
        board = board.replace("XX", "BB");

        if (board.contains("X")) {
            System.out.println("-1");
        } else {
            System.out.println(board);
        }

        scanner.close();
    }
}