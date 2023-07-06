import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String str;

        while(keyboard.hasNext()) {
            str = keyboard.nextLine();
            System.out.println(str);
        }
    }
}