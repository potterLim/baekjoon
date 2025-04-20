import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberCount; ++i) {
            int number = Integer.parseInt(reader.readLine());

            if (leftHeap.size() == rightHeap.size()) {
                leftHeap.offer(number);
            } else {
                rightHeap.offer(number);
            }

            if (!leftHeap.isEmpty() && !rightHeap.isEmpty() && leftHeap.peek() > rightHeap.peek()) {
                int leftTop = leftHeap.poll();
                int rightTop = rightHeap.poll();
                leftHeap.offer(rightTop);
                rightHeap.offer(leftTop);
            }

            output.append(leftHeap.peek()).append('\n');
        }

        System.out.print(output);
    }
}