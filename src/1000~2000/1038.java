import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetIndex = scanner.nextInt();

        if (targetIndex > 1022) {
            System.out.println(-1);
            return;
        }

        List<Long> decreasingNumbers = new ArrayList<>();

        for (int bitmask = 1; bitmask < (1 << 10); ++bitmask) {
            long decreasingNumber = 0L;

            for (int digit = 9; digit >= 0; --digit) {
                if ((bitmask & (1 << digit)) != 0) {
                    decreasingNumber = decreasingNumber * 10L + digit;
                }
            }

            decreasingNumbers.add(decreasingNumber);
        }

        Collections.sort(decreasingNumbers);
        System.out.println(decreasingNumbers.get(targetIndex));
    }
}