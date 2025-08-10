import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        long coefficientN = Long.parseLong(tokenizer.nextToken());
        long constantTerm = Long.parseLong(tokenizer.nextToken());

        long constantC = Long.parseLong(bufferedReader.readLine().trim());
        long startN = Long.parseLong(bufferedReader.readLine().trim());

        boolean isSatisfied = (coefficientN <= constantC) && (coefficientN * startN + constantTerm <= constantC * startN);

        if (isSatisfied) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
