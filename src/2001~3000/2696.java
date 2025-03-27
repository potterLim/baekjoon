import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < testCaseCount; ++i) {
            int sequenceLength = Integer.parseInt(bufferedReader.readLine());
            int medianCount = (sequenceLength + 1) / 2;
            System.out.println(medianCount);

            String[] tokens = new String[(sequenceLength / 10 + 1) * 10];
            int index = 0;
            while (index < sequenceLength) {
                String[] lineTokens = bufferedReader.readLine().split(" ");
                for (String token : lineTokens) {
                    if (!token.isEmpty()) {
                        tokens[index] = token;
                        ++index;
                    }
                }
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            int printed = 0;

            for (int j = 0; j < sequenceLength; ++j) {
                int number = Integer.parseInt(tokens[j]);

                if (maxHeap.isEmpty() || number <= maxHeap.peek()) {
                    maxHeap.add(number);
                } else {
                    minHeap.add(number);
                }

                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.add(maxHeap.poll());
                } else if (minHeap.size() > maxHeap.size()) {
                    maxHeap.add(minHeap.poll());
                }

                if (j % 2 == 0) {
                    System.out.print(maxHeap.peek());
                    ++printed;
                    if (printed % 10 == 0 || j == sequenceLength - 1) {
                        System.out.println();
                    } else {
                        System.out.print(" ");
                    }
                }
            }
        }
    }
}
