import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int sumDigits(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int digits = String.valueOf(n).length();
        int start = Math.max(1, n - 9 * digits);

        int answer = 0;
        for (int i = start; i < n; ++i) {
            if (i + sumDigits(i) == n) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
