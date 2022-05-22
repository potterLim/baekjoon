import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String num1Str = keyboard.next();
        String num2Str = keyboard.next();
        int num1=0;
        int num2=0;

        num1 += (num1Str.charAt(2) - '0') * 100;
        num1 += (num1Str.charAt(1) - '0') * 10;
        num1 += (num1Str.charAt(0) - '0');

        num2 += (num2Str.charAt(2) - '0') * 100;
        num2 += (num2Str.charAt(1) - '0') * 10;
        num2 += (num2Str.charAt(0) - '0');

        System.out.println((num1 > num2) ? num1 : num2);
    }
}
