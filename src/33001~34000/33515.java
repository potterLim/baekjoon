import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t1 = scanner.nextInt();
        int t2 = scanner.nextInt();

        int maxTime = Math.min(t1, t2);
        System.out.println(maxTime);
    }
}