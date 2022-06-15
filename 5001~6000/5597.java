import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int total_student_count = 30;
        boolean[] students = new boolean[total_student_count];
        int submitted_student;

        for (int i = 0; i < total_student_count - 2; i++) {
            submitted_student = keyboard.nextInt();
            students[submitted_student - 1] = true;
        }

        for (int i = 0; i < total_student_count; i++) {
            if (students[i] == false) {
                System.out.println(i + 1);
            }
        }


    }
}
