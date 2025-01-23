import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalNumbers = scanner.nextInt();
        int selectedNumbers = scanner.nextInt();
        int minMatchingNumbers = scanner.nextInt();
        scanner.close();

        double totalCombinations = calculateCombination(totalNumbers, selectedNumbers);
        double winningCombinations = 0;

        for (int matching = minMatchingNumbers; matching <= selectedNumbers; matching++) {
            if (totalNumbers - selectedNumbers < selectedNumbers - matching) {
                continue;
            }

            double userMatch = calculateCombination(selectedNumbers, matching);
            double computerMatch = calculateCombination(totalNumbers - selectedNumbers, selectedNumbers - matching);
            winningCombinations += userMatch * computerMatch;
        }

        double probability = winningCombinations / totalCombinations;
        System.out.printf("%.9f\n", probability);
    }

    private static double calculateCombination(int n, int r) {
        if (r > n || r < 0) {
            return 0;
        }

        double result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
            result /= (i + 1);
        }

        return result;
    }
}