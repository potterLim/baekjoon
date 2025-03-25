import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeOfSequence = scanner.nextInt();
        int[] sequence = new int[sizeOfSequence];
        for (int i = 0; i < sizeOfSequence; ++i) {
            sequence[i] = scanner.nextInt();
        }
        scanner.close();

        List<Integer> lisList = new ArrayList<>();
        for (int i = 0; i < sizeOfSequence; ++i) {
            int position = Collections.binarySearch(lisList, sequence[i]);
            if (position < 0) {
                position = -(position + 1);
            }
            if (position == lisList.size()) {
                lisList.add(sequence[i]);
            } else {
                lisList.set(position, sequence[i]);
            }
        }
        System.out.println(lisList.size());
    }
}