import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int count = 1;
        int count_setA = keyboard.nextInt();
        int count_setB = keyboard.nextInt();
        int[] A = new int[count_setA];
        int[] B = new int[count_setB];
        Set<Integer> A_sub_B_set = new HashSet<Integer>();
        Set<Integer> B_sub_A_set = new HashSet<Integer>();

        for (int i = 0; i < count_setA; i++) {
            A[i] = keyboard.nextInt();
            A_sub_B_set.add(A[i]);
        }

        for (int i = 0; i < count_setB; i++) {
            B[i] = keyboard.nextInt();
            B_sub_A_set.add(B[i]);
        }

        for (int i = 0; i < count_setA; i++) {
            B_sub_A_set.remove(A[i]);
        }

        for (int i = 0; i < count_setB; i++) {
            A_sub_B_set.remove(B[i]);
        }

        int result = A_sub_B_set.size() + B_sub_A_set.size();
        System.out.println(result);
    }
}