import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = reader.readLine();
        if (firstLine == null) {
            return;
        }

        StringTokenizer firstTokenizer = new StringTokenizer(firstLine);
        int personCount = Integer.parseInt(firstTokenizer.nextToken());
        int destinationM = Integer.parseInt(firstTokenizer.nextToken());

        ArrayList<int[]> backwardIntervals = new ArrayList<>();

        for (int i = 0; i < personCount; ++i) {
            StringTokenizer intervalTokenizer = new StringTokenizer(reader.readLine());
            int startPoint = Integer.parseInt(intervalTokenizer.nextToken());
            int endPoint = Integer.parseInt(intervalTokenizer.nextToken());

            if (startPoint > endPoint) {
                backwardIntervals.add(new int[]{ endPoint, startPoint });
            }
        }

        if (backwardIntervals.isEmpty()) {
            System.out.println(destinationM);
            return;
        }

        Collections.sort(backwardIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] firstInterval, int[] secondInterval) {
                if (firstInterval[0] == secondInterval[0]) {
                    return Integer.compare(firstInterval[1], secondInterval[1]);
                }
                return Integer.compare(firstInterval[0], secondInterval[0]);
            }
        });

        long totalBackwardLength = 0;
        int currentStart = backwardIntervals.get(0)[0];
        int currentEnd = backwardIntervals.get(0)[1];

        int intervalSize = backwardIntervals.size();
        for (int i = 1; i < intervalSize; ++i) {
            int nextStart = backwardIntervals.get(i)[0];
            int nextEnd = backwardIntervals.get(i)[1];

            if (nextStart <= currentEnd) {
                if (nextEnd > currentEnd) {
                    currentEnd = nextEnd;
                }
            } else {
                totalBackwardLength += (long) (currentEnd - currentStart);
                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }
        totalBackwardLength += (long) (currentEnd - currentStart);

        long result = (long) destinationM + (totalBackwardLength * 2);
        System.out.println(result);
    }
}