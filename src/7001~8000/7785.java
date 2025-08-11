import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int logCount = Integer.parseInt(line);

        Set<String> inOffice = new HashSet<String>();

        for (int i = 0; i < logCount; i++) {
            String entry = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(entry);
            String name = tokenizer.nextToken();
            String action = tokenizer.nextToken();

            if (action.equals("enter")) {
                inOffice.add(name);
            } else {
                inOffice.remove(name);
            }
        }

        List<String> names = new ArrayList<String>(inOffice);
        Collections.sort(names, Collections.reverseOrder());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < names.size(); i++) {
            writer.write(names.get(i));
            writer.newLine();
        }
        writer.flush();
    }
}
