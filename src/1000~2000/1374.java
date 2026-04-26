import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    private static class Lecture {
        private int mStartTime;
        private int mEndTime;

        public Lecture(int startTime, int endTime) {
            mStartTime = startTime;
            mEndTime = endTime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lectureCount = scanner.nextInt();
        Lecture[] lectures = new Lecture[lectureCount];

        for (int i = 0; i < lectureCount; ++i) {
            scanner.nextInt();

            int startTime = scanner.nextInt();
            int endTime = scanner.nextInt();

            lectures[i] = new Lecture(startTime, endTime);
        }

        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture lecture1, Lecture lecture2) {
                if (lecture1.mStartTime != lecture2.mStartTime) {
                    return Integer.compare(lecture1.mStartTime, lecture2.mStartTime);
                }

                return Integer.compare(lecture1.mEndTime, lecture2.mEndTime);
            }
        });

        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();

        for (int i = 0; i < lectureCount; ++i) {
            Lecture currentLecture = lectures[i];

            if (!endTimes.isEmpty() && endTimes.peek() <= currentLecture.mStartTime) {
                endTimes.poll();
            }

            endTimes.add(currentLecture.mEndTime);
        }

        System.out.println(endTimes.size());

        scanner.close();
    }
}