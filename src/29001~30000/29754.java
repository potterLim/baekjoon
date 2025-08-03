import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static final int MINUTES_PER_HOUR = 60;
    private static final int REQUIRED_COUNT = 5;
    private static final int REQUIRED_TIME = 60 * MINUTES_PER_HOUR;
    private static final int DAYS_IN_WEEK = 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int totalRecords = Integer.parseInt(tokenizer.nextToken());
        int lastDay = Integer.parseInt(tokenizer.nextToken());
        int weekCount = lastDay / DAYS_IN_WEEK;

        Map<String, int[][]> vtuberTable = new HashMap<>();

        for (int i = 0; i < totalRecords; ++i) {
            tokenizer = new StringTokenizer(reader.readLine());
            String name = tokenizer.nextToken();
            int day = Integer.parseInt(tokenizer.nextToken());
            String start = tokenizer.nextToken();
            String end = tokenizer.nextToken();

            int weekIndex = (day - 1) / DAYS_IN_WEEK;
            int startMinute = parseTimeToMinutes(start);
            int endMinute = parseTimeToMinutes(end);
            int duration = endMinute - startMinute;

            vtuberTable.putIfAbsent(name, new int[weekCount][2]);
            vtuberTable.get(name)[weekIndex][0] += 1;
            vtuberTable.get(name)[weekIndex][1] += duration;
        }

        List<String> realVtubers = new ArrayList<>();

        for (Map.Entry<String, int[][]> entry : vtuberTable.entrySet()) {
            String name = entry.getKey();
            int[][] weeks = entry.getValue();
            boolean isReal = true;

            for (int i = 0; i < weekCount; ++i) {
                if (weeks[i][0] < REQUIRED_COUNT || weeks[i][1] < REQUIRED_TIME) {
                    isReal = false;
                    break;
                }
            }

            if (isReal) {
                realVtubers.add(name);
            }
        }

        if (realVtubers.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(realVtubers);
            for (String name : realVtubers) {
                System.out.println(name);
            }
        }
    }

    private static int parseTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * MINUTES_PER_HOUR + minute;
    }
}
