import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int count_nohear = keyboard.nextInt();
        int count_nosee = keyboard.nextInt();
        int count_nohear_and_nosee = 0;
        HashSet<String> list_nohear = new HashSet<String>(count_nohear);

        for (int i = 0; i < count_nohear; ++i) {
            list_nohear.add(keyboard.next());
        }
        TreeSet<String> list_nohear_and_nosee = new TreeSet<String>();
        String no_see;
        for (int i = 0; i < count_nosee; ++i) {
            no_see = keyboard.next();
            if (list_nohear.contains(no_see) == true) {
                count_nohear_and_nosee++;
                list_nohear_and_nosee.add(no_see);
            }
        }

        System.out.println(count_nohear_and_nosee);
        for (String s : list_nohear_and_nosee) {
            System.out.println(s);
        }
    }
}