import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(st.nextToken());
        int targetSum = Integer.parseInt(st.nextToken());

        int[] cards = new int[cardCount];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < cardCount; ++i) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int bestSum = 0;

        for (int i = 0; i < cardCount - 2; ++i) {
            for (int j = i + 1; j < cardCount - 1; ++j) {
                for (int k = j + 1; k < cardCount; ++k) {
                    int sum = cards[i] + cards[j] + cards[k];

                    if (sum == targetSum) {
                        System.out.println(sum);
                        return;
                    }

                    if (sum < targetSum && sum > bestSum) {
                        bestSum = sum;
                    }
                }
            }
        }

        System.out.println(bestSum);
    }
}
