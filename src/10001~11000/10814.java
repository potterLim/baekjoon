import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        HashMap<Integer, ArrayList<String>> member = new HashMap<Integer, ArrayList<String>>();
        String str;
        int age;
        String name;

        int N = Integer.parseInt(keyboard.nextLine());
        for (int i = 0; i < N; ++i) {
            str = keyboard.nextLine().trim();
            age = Integer.parseInt(str.split(" ")[0]);
            name = str.split(" ")[1];

            if (member.containsKey(age)) {
                ArrayList<String> courses = member.get(age);
                courses.add(name);
            } else {
                ArrayList<String> courses = new ArrayList<String>();
                courses.add(name);
                member.put(age, courses);
            }
        }

        for (Integer i : member.keySet()) {
            for (String s : member.get(i)) {
                System.out.print(i + " " + s);
                System.out.println();
            }
        }
    }
}