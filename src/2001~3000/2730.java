import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < testCount; ++i) {
            String dueText = scanner.next();
            String submitText = scanner.next();

            int firstSlashIndex = dueText.indexOf('/');
            int secondSlashIndex = dueText.indexOf('/', firstSlashIndex + 1);

            int dueMonth = Integer.parseInt(dueText.substring(0, firstSlashIndex));
            int dueDay = Integer.parseInt(dueText.substring(firstSlashIndex + 1, secondSlashIndex));
            int dueYear = Integer.parseInt(dueText.substring(secondSlashIndex + 1));

            LocalDate dueDate = LocalDate.of(dueYear, dueMonth, dueDay);

            int submitSlashIndex = submitText.indexOf('/');

            int submitMonth = Integer.parseInt(submitText.substring(0, submitSlashIndex));
            int submitDay = Integer.parseInt(submitText.substring(submitSlashIndex + 1));

            LocalDate matchedDate = null;
            long matchedDiff = 0L;
            long minAbsDiff = Long.MAX_VALUE;

            for (int year = dueYear - 1; year <= dueYear + 1; ++year) {
                if (!isValidDate(year, submitMonth, submitDay)) {
                    continue;
                }

                LocalDate submitDate = LocalDate.of(year, submitMonth, submitDay);
                long diff = ChronoUnit.DAYS.between(dueDate, submitDate);
                long absDiff = getAbsoluteValue(diff);

                if (absDiff > 7L) {
                    continue;
                }

                if (absDiff < minAbsDiff) {
                    minAbsDiff = absDiff;
                    matchedDiff = diff;
                    matchedDate = submitDate;
                }
            }

            if (matchedDate == null) {
                builder.append("OUT OF RANGE");
            } else if (matchedDiff == 0L) {
                builder.append("SAME DAY");
            } else {
                builder.append(formatDate(matchedDate));
                builder.append(" IS ");
                builder.append(getAbsoluteValue(matchedDiff));
                builder.append(' ');

                if (getAbsoluteValue(matchedDiff) == 1L) {
                    builder.append("DAY ");
                } else {
                    builder.append("DAYS ");
                }

                if (matchedDiff < 0L) {
                    builder.append("PRIOR");
                } else {
                    builder.append("AFTER");
                }
            }

            if (i + 1 < testCount) {
                builder.append('\n');
            }
        }

        scanner.close();
        System.out.print(builder.toString());
    }

    private static boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            return false;
        }

        int lastDay = YearMonth.of(year, month).lengthOfMonth();
        return day >= 1 && day <= lastDay;
    }

    private static long getAbsoluteValue(long value) {
        if (value < 0L) {
            return -value;
        }

        return value;
    }

    private static String formatDate(LocalDate date) {
        return date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear();
    }
}