import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String[] tokens = reader.readLine().split(" ");
        final int length = Integer.parseInt(tokens[0]);
        int rotationLeft = Integer.parseInt(tokens[1]);

        final char[] dial = reader.readLine().toCharArray();

        for (int i = 0; i < length; ++i) {
            int distanceToA = (26 - (dial[i] - 'A')) % 26;

            if (rotationLeft >= distanceToA) {
                dial[i] = 'A';
                rotationLeft -= distanceToA;
            }
        }

        dial[length - 1] = (char) ((dial[length - 1] - 'A' + rotationLeft) % 26 + 'A');

        System.out.println(new String(dial));
    }
}
