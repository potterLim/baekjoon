import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final int[] DAYS_IN_MONTH_COMMON_YEAR = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int testCaseCount = scanner.nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            int bookingCount = scanner.nextInt();
            int cleaningMinutes = scanner.nextInt();

            int[] startTimes = new int[bookingCount];
            int[] endTimes = new int[bookingCount];

            for (int j = 0; j < bookingCount; ++j) {
                scanner.next();
                String checkInDate = scanner.next();
                String checkInTime = scanner.next();
                String checkOutDate = scanner.next();
                String checkOutTime = scanner.next();

                startTimes[j] = convertToMinutes(checkInDate, checkInTime);
                endTimes[j] = convertToMinutes(checkOutDate, checkOutTime) + cleaningMinutes;
            }

            Arrays.sort(startTimes);
            Arrays.sort(endTimes);

            int currentRoomCount = 0;
            int maximumRoomCount = 0;
            int startIndex = 0;
            int endIndex = 0;

            while (startIndex < bookingCount) {
                if (endIndex < bookingCount && endTimes[endIndex] <= startTimes[startIndex]) {
                    --currentRoomCount;
                    ++endIndex;
                } else {
                    ++currentRoomCount;

                    if (currentRoomCount > maximumRoomCount) {
                        maximumRoomCount = currentRoomCount;
                    }

                    ++startIndex;
                }
            }

            resultBuilder.append(maximumRoomCount);

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }

    private static int convertToMinutes(String date, String time) {
        int year = parseTwoOrFourDigits(date, 0, 4);
        int month = parseTwoOrFourDigits(date, 5, 7);
        int day = parseTwoOrFourDigits(date, 8, 10);
        int hour = parseTwoOrFourDigits(time, 0, 2);
        int minute = parseTwoOrFourDigits(time, 3, 5);

        int totalDays = 0;

        for (int i = 2013; i < year; ++i) {
            totalDays += 365;

            if (isLeapYear(i)) {
                ++totalDays;
            }
        }

        for (int i = 1; i < month; ++i) {
            totalDays += DAYS_IN_MONTH_COMMON_YEAR[i - 1];

            if (i == 2 && isLeapYear(year)) {
                ++totalDays;
            }
        }

        totalDays += day - 1;

        return totalDays * 24 * 60 + hour * 60 + minute;
    }

    private static int parseTwoOrFourDigits(String text, int startIndex, int endIndex) {
        int value = 0;

        for (int i = startIndex; i < endIndex; ++i) {
            value = value * 10 + text.charAt(i) - '0';
        }

        return value;
    }

    private static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }

        if (year % 100 == 0) {
            return false;
        }

        return year % 4 == 0;
    }

    private static final class FastScanner {
        private final BufferedInputStream mInputStream = new BufferedInputStream(System.in);
        private final byte[] mBuffer = new byte[1 << 16];
        private int mBufferSize = 0;
        private int mBufferIndex = 0;

        public int nextInt() throws IOException {
            int currentByte = read();

            while (currentByte <= ' ') {
                currentByte = read();
            }

            int value = 0;

            while (currentByte > ' ') {
                value = value * 10 + currentByte - '0';
                currentByte = read();
            }

            return value;
        }

        public String next() throws IOException {
            int currentByte = read();

            while (currentByte <= ' ') {
                currentByte = read();
            }

            StringBuilder builder = new StringBuilder();

            while (currentByte > ' ') {
                builder.append((char) currentByte);
                currentByte = read();
            }

            return builder.toString();
        }

        private int read() throws IOException {
            if (mBufferIndex >= mBufferSize) {
                mBufferSize = mInputStream.read(mBuffer);
                mBufferIndex = 0;
            }

            if (mBufferSize == -1) {
                return -1;
            }

            return mBuffer[mBufferIndex++];
        }
    }
}