import java.util.Scanner;

public class Main {
    private static final int SCALE = 1000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int bucketCount = scanner.nextInt();
        int[] counts = new int[bucketCount];

        while (scanner.hasNext()) {
            String valueText = scanner.next();
            int scaledValue = parseScaledValue(valueText);
            int bucketIndex = scaledValue * bucketCount / SCALE;

            ++counts[bucketIndex];
        }

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < bucketCount; ++i) {
            if (i > 0) {
                resultBuilder.append(' ');
            }

            resultBuilder.append(counts[i]);
        }

        System.out.println(resultBuilder);
    }

    private static int parseScaledValue(String valueText) {
        int dotIndex = valueText.indexOf('.');
        int integerPart = 0;
        int fractionalPart = 0;

        if (dotIndex == -1) {
            integerPart = Integer.parseInt(valueText);
        } else {
            integerPart = Integer.parseInt(valueText.substring(0, dotIndex));

            int digitCount = 0;

            for (int i = dotIndex + 1; i < valueText.length() && digitCount < 6; ++i) {
                fractionalPart = fractionalPart * 10 + valueText.charAt(i) - '0';
                ++digitCount;
            }

            while (digitCount < 6) {
                fractionalPart *= 10;
                ++digitCount;
            }
        }

        return integerPart * SCALE + fractionalPart;
    }
}