import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 1_000_000_000;
    private static final int TARGET_STEPS = 30;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int low = MIN_VALUE;
        int high = MAX_VALUE;

        int stepCount = 0;

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                return;
            }

            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            int guess = Integer.parseInt(line);
            stepCount++;

            if (stepCount >= TARGET_STEPS) {
                if (guess >= low && guess <= high) {
                    System.out.println("=");
                    System.out.flush();
                    return;
                }

                if (guess < low) {
                    System.out.println(">");
                    System.out.flush();
                    continue;
                }

                System.out.println("<");
                System.out.flush();
                continue;
            }

            long mid = ((long) low + (long) high) / 2L;

            if (guess < mid) {
                if (guess + 1 <= high) {
                    low = Math.max(low, guess + 1);
                    System.out.println(">");
                    System.out.flush();
                    continue;
                }

                high = Math.min(high, guess - 1);
                System.out.println("<");
                System.out.flush();
                continue;
            }

            if (guess > mid) {
                if (guess - 1 >= low) {
                    high = Math.min(high, guess - 1);
                    System.out.println("<");
                    System.out.flush();
                    continue;
                }

                low = Math.max(low, guess + 1);
                System.out.println(">");
                System.out.flush();
                continue;
            }

            if (guess + 1 <= high) {
                low = Math.max(low, guess + 1);
                System.out.println(">");
                System.out.flush();
            } else {
                high = Math.min(high, guess - 1);
                System.out.println("<");
                System.out.flush();
            }
        }
    }
}
