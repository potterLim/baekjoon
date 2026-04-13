import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    private static final int MAX_VALUE = 2047;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startValue = Integer.parseInt(scanner.next(), 2);
        int targetValue = Integer.parseInt(scanner.next(), 2);

        int[] distanceByValue = new int[MAX_VALUE + 1];

        for (int i = 0; i <= MAX_VALUE; ++i) {
            distanceByValue[i] = -1;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(startValue);
        distanceByValue[startValue] = 0;

        while (!queue.isEmpty()) {
            int currentValue = queue.poll();

            if (currentValue == targetValue) {
                break;
            }

            int currentDistance = distanceByValue[currentValue];
            int bitLength = 0;
            int temporaryValue = currentValue;

            if (temporaryValue == 0) {
                bitLength = 1;
            } else {
                while (temporaryValue > 0) {
                    ++bitLength;
                    temporaryValue >>= 1;
                }
            }

            for (int i = 0; i < bitLength - 1; ++i) {
                int nextValue = currentValue ^ (1 << i);

                if (distanceByValue[nextValue] == -1) {
                    distanceByValue[nextValue] = currentDistance + 1;
                    queue.add(nextValue);
                }
            }

            if (currentValue < MAX_VALUE) {
                int nextValue = currentValue + 1;

                if (distanceByValue[nextValue] == -1) {
                    distanceByValue[nextValue] = currentDistance + 1;
                    queue.add(nextValue);
                }
            }

            if (currentValue > 0) {
                int nextValue = currentValue - 1;

                if (distanceByValue[nextValue] == -1) {
                    distanceByValue[nextValue] = currentDistance + 1;
                    queue.add(nextValue);
                }
            }
        }

        System.out.println(distanceByValue[targetValue]);
    }
}