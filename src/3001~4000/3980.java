import java.util.Scanner;

public class Main {
    private static final int PLAYER_COUNT = 11;
    private static int[][] sAbilities;
    private static int sMaximumAbilitySum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            sAbilities = new int[PLAYER_COUNT][PLAYER_COUNT];

            for (int j = 0; j < PLAYER_COUNT; ++j) {
                for (int k = 0; k < PLAYER_COUNT; ++k) {
                    sAbilities[j][k] = scanner.nextInt();
                }
            }

            sMaximumAbilitySum = 0;
            searchMaximumAbilitySum(0, 0, 0);

            resultBuilder.append(sMaximumAbilitySum);

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }

    private static void searchMaximumAbilitySum(int playerIndex, int usedPositionMask, int currentAbilitySum) {
        if (playerIndex == PLAYER_COUNT) {
            if (currentAbilitySum > sMaximumAbilitySum) {
                sMaximumAbilitySum = currentAbilitySum;
            }
            return;
        }

        for (int i = 0; i < PLAYER_COUNT; ++i) {
            if ((usedPositionMask & (1 << i)) != 0) {
                continue;
            }

            if (sAbilities[playerIndex][i] == 0) {
                continue;
            }

            searchMaximumAbilitySum(playerIndex + 1, usedPositionMask | (1 << i), currentAbilitySum + sAbilities[playerIndex][i]);
        }
    }
}