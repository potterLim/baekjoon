import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static int sLength;
    private static HashMap<String, Boolean> sMemo = new HashMap<String, Boolean>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        sLength = s.length();

        for (int i = sLength - 1; i >= 0; --i) {
            if (s.charAt(i) == '1') {
                continue;
            }

            String prefix = s.substring(0, i) + '1';

            if (canExtend(prefix) == false) {
                continue;
            }

            StringBuilder builder = new StringBuilder(prefix);

            while (builder.length() < sLength) {
                String zeroCandidate = builder.toString() + '0';

                if (canExtend(zeroCandidate)) {
                    builder.append('0');
                } else {
                    builder.append('1');
                }
            }

            System.out.println(builder.toString());
            return;
        }

        System.out.println(-1);
    }

    private static boolean canExtend(String prefix) {
        Boolean cached = sMemo.get(prefix);

        if (cached != null) {
            return cached.booleanValue();
        }

        if (isPartiallyValid(prefix) == false) {
            sMemo.put(prefix, false);
            return false;
        }

        if (prefix.length() == sLength) {
            boolean isSpecial = isSpecial(prefix);
            sMemo.put(prefix, isSpecial);
            return isSpecial;
        }

        if (canExtend(prefix + '0')) {
            sMemo.put(prefix, true);
            return true;
        }

        if (canExtend(prefix + '1')) {
            sMemo.put(prefix, true);
            return true;
        }

        sMemo.put(prefix, false);
        return false;
    }

    private static boolean isPartiallyValid(String s) {
        int length = s.length();

        for (int split = 1; split < length; ++split) {
            int compareCount = Math.min(split, length - split);

            for (int i = 0; i < compareCount; ++i) {
                char left = s.charAt(i);
                char right = s.charAt(split + i);

                if (left < right) {
                    break;
                }

                if (left > right) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isSpecial(String s) {
        int length = s.length();

        for (int split = 1; split < length; ++split) {
            int leftIndex = 0;
            int rightIndex = split;

            while (leftIndex < split && rightIndex < length) {
                char left = s.charAt(leftIndex);
                char right = s.charAt(rightIndex);

                if (left < right) {
                    break;
                }

                if (left > right) {
                    return false;
                }

                ++leftIndex;
                ++rightIndex;
            }

            if (leftIndex == split) {
                if (split >= length - split) {
                    return false;
                }
            } else if (rightIndex == length) {
                return false;
            }
        }

        return true;
    }
}