import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();

        for (int i = 0; i < testCaseCount; i++) {
            int n = scanner.nextInt();
            int elementCount = scanner.nextInt();

            ArrayList<Integer> elements = new ArrayList<>(elementCount);
            boolean containsOne = false;

            for (int j = 0; j < elementCount; j++) {
                int value = scanner.nextInt();
                if (value == 1) {
                    containsOne = true;
                } else {
                    elements.add(value);
                }
            }

            if (!containsOne) {
                elements.add(0, 1);
            }

            int mappedCount = elements.size();

            System.out.println(n + " " + mappedCount);

            if (mappedCount == 0) {
                System.out.println();
                continue;
            }

            StringBuilder line = new StringBuilder();
            for (int j = 0; j < mappedCount; j++) {
                if (j > 0) {
                    line.append(' ');
                }
                line.append(elements.get(j));
            }

            System.out.println(line.toString());
        }
    }
}