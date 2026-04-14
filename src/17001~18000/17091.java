import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int h = scanner.nextInt();
        int m = scanner.nextInt();

        if (m == 0) {
            System.out.println(getHourWord(h) + " o' clock");
            return;
        }

        if (m <= 30) {
            if (m == 15) {
                System.out.println("quarter past " + getHourWord(h));
            } else if (m == 30) {
                System.out.println("half past " + getHourWord(h));
            } else {
                System.out.println(getMinuteWord(m) + " past " + getHourWord(h));
            }

            return;
        }

        int nextHour = h + 1;
        if (nextHour == 13) {
            nextHour = 1;
        }

        int remainMinutes = 60 - m;

        if (remainMinutes == 15) {
            System.out.println("quarter to " + getHourWord(nextHour));
        } else {
            System.out.println(getMinuteWord(remainMinutes) + " to " + getHourWord(nextHour));
        }
    }

    private static String getMinuteWord(int minute) {
        if (minute == 1) {
            return "one minute";
        }

        return getNumberWord(minute) + " minutes";
    }

    private static String getHourWord(int hour) {
        return getNumberWord(hour);
    }

    private static String getNumberWord(int number) {
        String[] words = {
                "",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine",
                "ten",
                "eleven",
                "twelve",
                "thirteen",
                "fourteen",
                "fifteen",
                "sixteen",
                "seventeen",
                "eighteen",
                "nineteen",
                "twenty",
                "twenty one",
                "twenty two",
                "twenty three",
                "twenty four",
                "twenty five",
                "twenty six",
                "twenty seven",
                "twenty eight",
                "twenty nine"
        };

        return words[number];
    }
}