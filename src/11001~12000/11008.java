import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());

        while (t-- > 0) {
            String[] input = scanner.nextLine().split(" ");
            String s = input[0];
            String p = input[1];

            int count = 0;
            int idx = 0;

            while (idx <= s.length() - p.length()) {
                if (s.substring(idx, idx + p.length()).equals(p)) {
                    count++;
                    idx += p.length();
                } else {
                    idx++;
                }
            }

            int totalTime = s.length() - (count * (p.length() - 1));
            System.out.println(totalTime);
        }
    }
}