import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(firstLine);
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        String[] indexToName = new String[n + 1];
        Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

        for (int i = 1; i <= n; i++) {
            String name = reader.readLine();
            indexToName[i] = name;
            nameToIndex.put(name, i);
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            String query = reader.readLine();

            if (isDigit(query.charAt(0))) {
                int idx = Integer.parseInt(query);
                sb.append(indexToName[idx]);
                sb.append('\n');
            } else {
                Integer idx = nameToIndex.get(query);
                sb.append(idx);
                sb.append('\n');
            }
        }

        writer.write(sb.toString());
        writer.flush();
    }

    private static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }
}
