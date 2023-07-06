import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int[][] matrix = new int[9][9];

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j <9; j++) {
                matrix[i][j] = keyboard.nextInt();
            }
        }

        int max_row = 0;
        int max_col = 0;
        int max_num = -1;

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j <9; j++) {
                if (matrix[i][j] > max_num) {
                    max_row = i + 1;
                    max_col = j + 1;
                    max_num = matrix[i][j];
                }
            }
        }

        System.out.println(max_num);
        System.out.print(max_row + " ");
        System.out.println(max_col);

    }
}
