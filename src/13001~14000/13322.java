import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            System.out.println(i);
        }
    }
}
