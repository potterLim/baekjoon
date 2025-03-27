import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    private static class Problem implements Comparable<Problem> {
        int deadline;
        int ramen;

        public Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem other) {
            return Integer.compare(this.deadline, other.deadline);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int problemCount = Integer.parseInt(bufferedReader.readLine());
        Problem[] problems = new Problem[problemCount];

        for (int i = 0; i < problemCount; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int deadline = Integer.parseInt(tokenizer.nextToken());
            int ramen = Integer.parseInt(tokenizer.nextToken());
            problems[i] = new Problem(deadline, ramen);
        }

        Arrays.sort(problems);

        PriorityQueue<Integer> ramenHeap = new PriorityQueue<>();

        for (int i = 0; i < problemCount; ++i) {
            ramenHeap.offer(problems[i].ramen);

            if (ramenHeap.size() > problems[i].deadline) {
                ramenHeap.poll();
            }
        }

        int totalRamen = 0;

        while (!ramenHeap.isEmpty()) {
            totalRamen += ramenHeap.poll();
        }

        System.out.println(totalRamen);
    }
}
