import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int characterCount = scanner.nextInt();
        int attributeCount = scanner.nextInt();
        int maxQuestions = scanner.nextInt();

        int[] maxValueByAttribute = new int[attributeCount + 1];
        for (int i = 1; i <= attributeCount; i++) {
            maxValueByAttribute[i] = scanner.nextInt();
        }

        int[] chosenValueByAttribute = new int[attributeCount + 1];

        for (int i = 1; i <= attributeCount; i++) {
            chosenValueByAttribute[i] = 1;
        }

        int questionsUsed = 0;

        for (int i = 1; i <= characterCount; i++) {
            if (questionsUsed >= maxQuestions) {
                break;
            }

            System.out.println("? " + i + " " + i);
            System.out.flush();

            int observedValue = scanner.nextInt();
            questionsUsed++;

            int maxValue = maxValueByAttribute[i];
            int differentValue = (observedValue % maxValue) + 1;

            chosenValueByAttribute[i] = differentValue;
        }

        StringBuilder output = new StringBuilder();
        output.append('!');

        for (int i = 1; i <= attributeCount; i++) {
            output.append(' ');
            output.append(chosenValueByAttribute[i]);
        }

        System.out.println(output.toString());
        System.out.flush();
    }
}
