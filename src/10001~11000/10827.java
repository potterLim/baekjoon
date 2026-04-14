import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.next();
        int b = scanner.nextInt();

        int dotIndex = a.indexOf('.');
        int fractionalLength = a.length() - dotIndex - 1;

        String numberString = a.substring(0, dotIndex) + a.substring(dotIndex + 1);
        BigInteger base = new BigInteger(numberString);
        BigInteger result = base.pow(b);

        int totalFractionalLength = fractionalLength * b;
        String resultString = result.toString();

        if (totalFractionalLength == 0) {
            System.out.println(resultString);
            return;
        }

        if (resultString.length() <= totalFractionalLength) {
            StringBuilder builder = new StringBuilder();

            builder.append("0.");

            for (int i = 0; i < totalFractionalLength - resultString.length(); ++i) {
                builder.append('0');
            }

            builder.append(resultString);

            System.out.println(trimNumber(builder.toString()));
            return;
        }

        int splitIndex = resultString.length() - totalFractionalLength;
        String integerPart = resultString.substring(0, splitIndex);
        String fractionalPart = resultString.substring(splitIndex);

        System.out.println(trimNumber(integerPart + "." + fractionalPart));
    }

    private static String trimNumber(String number) {
        int startIndex = 0;

        while (startIndex < number.length() - 1
                && number.charAt(startIndex) == '0'
                && number.charAt(startIndex + 1) != '.') {
            ++startIndex;
        }

        int endIndex = number.length() - 1;

        while (endIndex >= 0 && number.charAt(endIndex) == '0') {
            --endIndex;
        }

        if (endIndex >= 0 && number.charAt(endIndex) == '.') {
            --endIndex;
        }

        return number.substring(startIndex, endIndex + 1);
    }
}