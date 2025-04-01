import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCount; ++i) {
            int stringCount = Integer.parseInt(scanner.nextLine());

            List<String> stringList = new ArrayList<>();

            for (int j = 0; j < stringCount; ++j) {
                String input = scanner.nextLine();
                stringList.add(input);
            }

            Collections.sort(stringList, new CustomStringComparator());

            for (String str : stringList) {
                System.out.println(str);
            }
        }

        scanner.close();
    }

    private static boolean isValidInput(String input) {
        if (input.length() == 0 || input.length() > 50) {
            return false;
        }

        for (char ch : input.toCharArray()) {
            if (!Character.isLetter(ch) && ch != '-') {
                return false;
            }
        }

        return true;
    }

    private static class CustomStringComparator implements Comparator<String> {
        @Override
        public int compare(String x, String y) {
            if (x.startsWith(y)) {
                return 1;
            }

            if (y.startsWith(x)) {
                return -1;
            }

            int length = Math.min(x.length(), y.length());

            for (int i = 0; i < length; ++i) {
                char chX = x.charAt(i);
                char chY = y.charAt(i);

                if (chX == chY) {
                    continue;
                }

                boolean isXHyphen = (chX == '-');
                boolean isYHyphen = (chY == '-');

                if (isXHyphen != isYHyphen) {
                    return isXHyphen ? 1 : -1;
                }

                char upperX = Character.toUpperCase(chX);
                char upperY = Character.toUpperCase(chY);

                if (upperX != upperY) {
                    return Character.compare(upperX, upperY);
                }

                boolean isXUpper = Character.isUpperCase(chX);
                boolean isYUpper = Character.isUpperCase(chY);

                if (isXUpper != isYUpper) {
                    return isXUpper ? -1 : 1;
                }
            }

            return Integer.compare(x.length(), y.length());
        }
    }
}
