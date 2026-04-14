import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static String sExpression;
    private static int sIndex;
    private static HashMap<Long, Integer> sCountMap;
    private static int sLeafCount;
    private static int sMaxCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();

        StringBuilder builder = new StringBuilder();

        for (int test = 0; test < testCount; ++test) {
            sExpression = scanner.next();
            sIndex = 0;
            sCountMap = new HashMap<Long, Integer>();
            sLeafCount = 0;
            sMaxCount = 0;

            parse(0);

            builder.append(sLeafCount - sMaxCount).append('\n');
        }

        System.out.print(builder.toString());
    }

    private static void parse(int depth) {
        char ch = sExpression.charAt(sIndex);

        if (ch == '[') {
            ++sIndex;
            parse(depth + 1);
            ++sIndex;
            parse(depth + 1);
            ++sIndex;
            return;
        }

        long weight = 0L;

        while (sIndex < sExpression.length()) {
            ch = sExpression.charAt(sIndex);

            if (ch < '0' || ch > '9') {
                break;
            }

            weight = weight * 10L + (ch - '0');
            ++sIndex;
        }

        long scaledWeight = weight << depth;

        Integer oldCount = sCountMap.get(scaledWeight);
        int newCount;

        if (oldCount == null) {
            newCount = 1;
        } else {
            newCount = oldCount + 1;
        }

        sCountMap.put(scaledWeight, newCount);

        if (newCount > sMaxCount) {
            sMaxCount = newCount;
        }

        ++sLeafCount;
    }
}