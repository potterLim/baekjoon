import java.util.Scanner;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static class Person implements Comparable<Person> {
        int start;
        int end;

        Person(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Person other) {
            return Integer.compare(this.start, other.start);
        }
    }

    static class ComputerUse {
        int endTime;
        int computerId;

        ComputerUse(int endTime, int computerId) {
            this.endTime = endTime;
            this.computerId = computerId;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Person[] people = new Person[n];

        for (int i = 0; i < n; ++i) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            people[i] = new Person(start, end);
        }

        Arrays.sort(people);

        PriorityQueue<ComputerUse> using = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.endTime, b.endTime)
        );
        PriorityQueue<Integer> available = new PriorityQueue<>();

        List<Integer> usageCount = new ArrayList<>();
        int nextComputerId = 0;

        for (int i = 0; i < n; ++i) {
            int currentStart = people[i].start;

            while (!using.isEmpty() && using.peek().endTime <= currentStart) {
                available.offer(using.poll().computerId);
            }

            int computerId;
            if (!available.isEmpty()) {
                computerId = available.poll();
            } else {
                computerId = nextComputerId;
                nextComputerId++;
                usageCount.add(0);
            }

            usageCount.set(computerId, usageCount.get(computerId) + 1);
            using.offer(new ComputerUse(people[i].end, computerId));
        }

        System.out.println(nextComputerId);
        for (int i = 0; i < nextComputerId; ++i) {
            System.out.print(usageCount.get(i));
            if (i < nextComputerId - 1) {
                System.out.print(" ");
            }
        }
    }
}
