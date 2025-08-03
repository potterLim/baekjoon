import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final int testCaseCount = Integer.parseInt(reader.readLine());

        final Pattern pattern = Pattern.compile("^(100+1+|01)+$");

        for (int i = 0; i < testCaseCount; ++i) {
            final String wave = reader.readLine();
            final Matcher matcher = pattern.matcher(wave);

            if (matcher.matches()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
