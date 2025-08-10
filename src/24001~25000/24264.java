import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        long n = Long.parseLong(new BufferedReader(new InputStreamReader(System.in)).readLine().trim());
        System.out.println(n * n);
        System.out.println(2);
    }
}
