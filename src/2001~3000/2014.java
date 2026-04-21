import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int primeCount = scanner.nextInt();
        int targetIndex = scanner.nextInt();

        int[] primes = new int[primeCount];
        for (int i = 0; i < primeCount; ++i) {
            primes[i] = scanner.nextInt();
        }

        scanner.close();

        long[] numbers = new long[targetIndex + 1];
        int[] indexes = new int[primeCount];

        numbers[0] = 1L;

        for (int i = 1; i <= targetIndex; ++i) {
            long nextNumber = Long.MAX_VALUE;

            for (int j = 0; j < primeCount; ++j) {
                long candidate = numbers[indexes[j]] * primes[j];
                if (candidate < nextNumber) {
                    nextNumber = candidate;
                }
            }

            numbers[i] = nextNumber;

            for (int j = 0; j < primeCount; ++j) {
                while (numbers[indexes[j]] * primes[j] <= nextNumber) {
                    ++indexes[j];
                }
            }
        }

        System.out.print(numbers[targetIndex]);
    }
}