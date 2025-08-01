import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean isCGrade(String grade) {
        return grade.equals("C+") || grade.equals("C0") || grade.equals("C-") || grade.equals("C");
    }

    private static boolean isB0OrBMinus(String grade) {
        return grade.equals("B0") || grade.equals("B-") || grade.equals("B");
    }

    private static boolean isBPlusOrAMinus(String grade) {
        return grade.equals("B+") || grade.equals("A-");
    }

    private static boolean isA0OrA(String grade) {
        return grade.equals("A0") || grade.equals("A");
    }

    private static boolean isAPlus(String grade) {
        return grade.equals("A+");
    }

    private static List<String> parseGrades(String input) {
        List<String> grades = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            char ch = input.charAt(i);
            if (i + 1 < input.length()) {
                char next = input.charAt(i + 1);
                if (next == '+' || next == '-' || next == '0') {
                    grades.add("" + ch + next);
                    i += 2;
                    continue;
                }
            }
            grades.add("" + ch);
            i++;
        }
        return grades;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int semesterCount = Integer.parseInt(scanner.nextLine());
        String rawGrades = scanner.nextLine();

        List<String> grades = parseGrades(rawGrades);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < semesterCount; i++) {
            String current = grades.get(i);
            String prev = (i == 0) ? null : grades.get(i - 1);

            if (isCGrade(current)) {
                result.append("B");
            } else if (isB0OrBMinus(current)) {
                if (i == 0 || isCGrade(prev)) {
                    result.append("D");
                } else {
                    result.append("B");
                }
            } else if (isBPlusOrAMinus(current)) {
                if (i == 0 || isB0OrBMinus(prev) || isCGrade(prev)) {
                    result.append("P");
                } else {
                    result.append("D");
                }
            } else if (isA0OrA(current)) {
                if (i == 0 || isBPlusOrAMinus(prev) || isB0OrBMinus(prev) || isCGrade(prev)) {
                    result.append("E");
                } else {
                    result.append("P");
                }
            } else if (isAPlus(current)) {
                result.append("E");
            }
        }

        System.out.println(result.toString());
    }
}
