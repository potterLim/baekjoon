import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int coworkerCount = scanner.nextInt();
        String meetingTime = scanner.next();
        String yjTimeZone = scanner.next();

        int baseMinutes = parseTimeToMinutes(meetingTime);
        int yjOffsetMinutes = parseUtcOffsetToMinutes(yjTimeZone);
        int utcMinutes = baseMinutes - yjOffsetMinutes;

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < coworkerCount; ++i) {
            String coworkerTimeZone = scanner.next();
            int coworkerOffsetMinutes = parseUtcOffsetToMinutes(coworkerTimeZone);
            int localMinutes = utcMinutes + coworkerOffsetMinutes;
            int normalizedMinutes = normalizeMinutes(localMinutes);

            int hour = normalizedMinutes / 60;
            int minute = normalizedMinutes % 60;

            if (hour < 10) {
                resultBuilder.append('0');
            }
            resultBuilder.append(hour).append(':');

            if (minute < 10) {
                resultBuilder.append('0');
            }
            resultBuilder.append(minute);

            if (i + 1 < coworkerCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }

    private static int parseTimeToMinutes(String time) {
        int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return hour * 60 + minute;
    }

    private static int parseUtcOffsetToMinutes(String utcOffset) {
        int sign = 1;

        if (utcOffset.charAt(3) == '-') {
            sign = -1;
        }

        String offsetText = utcOffset.substring(4);
        int hourPart;
        int minutePart = 0;

        int dotIndex = offsetText.indexOf('.');

        if (dotIndex == -1) {
            hourPart = Integer.parseInt(offsetText);
        } else {
            hourPart = Integer.parseInt(offsetText.substring(0, dotIndex));
            minutePart = 30;
        }

        return sign * (hourPart * 60 + minutePart);
    }

    private static int normalizeMinutes(int minutes) {
        int minutesPerDay = 24 * 60;
        int normalizedMinutes = minutes % minutesPerDay;

        if (normalizedMinutes < 0) {
            normalizedMinutes += minutesPerDay;
        }

        return normalizedMinutes;
    }
}