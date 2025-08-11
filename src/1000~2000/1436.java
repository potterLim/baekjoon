import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean has666(int x) {
        return String.valueOf(x).contains("666");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int count = 0;
        int num = 665;
        while (count < n) {
            ++num;
            if (has666(num)) {
                ++count;
            }
        }

        System.out.println(num);
    }
}
