import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int commandCount = scanner.nextInt();

        long[] times = new long[commandCount + 1];
        String[] texts = new String[commandCount + 1];

        texts[0] = "";
        times[0] = -1;

        for (int i = 1; i <= commandCount; ++i) {
            String command = scanner.next();

            if (command.equals("type")) {
                String character = scanner.next();
                long time = scanner.nextLong();

                times[i] = time;
                texts[i] = texts[i - 1] + character;
            } else {
                long undoTime = scanner.nextLong();
                long time = scanner.nextLong();

                times[i] = time;

                long targetTime = time - undoTime;
                int targetIndex = 0;

                for (int j = i - 1; j >= 0; --j) {
                    if (times[j] < targetTime) {
                        targetIndex = j;
                        break;
                    }
                }

                texts[i] = texts[targetIndex];
            }
        }

        System.out.println(texts[commandCount]);

        scanner.close();
    }
}