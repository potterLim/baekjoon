import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        if (line == null) {
            return;
        }

        int lineCount = Integer.parseInt(line);
        int[][] lines = new int[lineCount][2];

        for (int i = 0; i < lineCount; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            lines[i][0] = Integer.parseInt(tokenizer.nextToken());
            lines[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(lines, new Comparator<int[]>() {
            @Override
            public int compare(int[] firstLine, int[] secondLine) {
                if (firstLine[0] == secondLine[0]) {
                    return Integer.compare(firstLine[1], secondLine[1]);
                }
                return Integer.compare(firstLine[0], secondLine[0]);
            }
        });

        long totalLength = 0;
        int currentStart = lines[0][0];
        int currentEnd = lines[0][1];

        for (int i = 1; i < lineCount; ++i) {
            int nextStart = lines[i][0];
            int nextEnd = lines[i][1];

            if (nextStart <= currentEnd) {
                if (nextEnd > currentEnd) {
                    currentEnd = nextEnd;
                }
            } else {
                totalLength += (long) (currentEnd - currentStart);
                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }

        totalLength += (long) (currentEnd - currentStart);

        writer.write(String.valueOf(totalLength));
        writer.newLine();

        writer.flush();
        writer.close();
        reader.close();
    }
}