import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int testCaseCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCaseCount; ++i) {
            final char[] inputWord = scanner.nextLine().toCharArray();

            if (generateNextPermutation(inputWord)) {
                System.out.println(new String(inputWord));
            } else {
                System.out.println(new String(inputWord));
            }
        }
    }

    private static boolean generateNextPermutation(char[] characters) {
        int pivotIndex = characters.length - 1;

        while (pivotIndex > 0 && characters[pivotIndex - 1] >= characters[pivotIndex]) {
            pivotIndex--;
        }

        if (pivotIndex <= 0) {
            return false;
        }

        int swapIndex = characters.length - 1;
        while (characters[swapIndex] <= characters[pivotIndex - 1]) {
            swapIndex--;
        }

        swap(characters, pivotIndex - 1, swapIndex);

        int left = pivotIndex;
        int right = characters.length - 1;
        while (left < right) {
            swap(characters, left, right);
            left++;
            right--;
        }

        return true;
    }

    private static void swap(char[] array, int i, int j) {
        final char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
