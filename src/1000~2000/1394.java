import java.util.Scanner;

public class Main {
    private static final int MOD = 900528;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String alphabet = scanner.next();
        String password = scanner.next();

        int[] orderMap = new int[65536];
        int alphabetLength = alphabet.length();

        for (int i = 0; i < alphabetLength; ++i) {
            orderMap[alphabet.charAt(i)] = i;
        }

        int passwordLength = password.length();

        long answer = 0L;
        long power = 1L;

        for (int i = passwordLength - 1; i >= 0; --i) {
            int order = orderMap[password.charAt(i)];
            answer += power * order;
            answer %= MOD;

            power *= alphabetLength;
            power %= MOD;
        }

        answer += 1L;
        answer %= MOD;

        long shorterCount = 0L;
        long current = alphabetLength % MOD;

        for (int i = 1; i < passwordLength; ++i) {
            shorterCount += current;
            shorterCount %= MOD;

            current *= alphabetLength;
            current %= MOD;
        }

        answer += shorterCount;
        answer %= MOD;

        System.out.println(answer);
    }
}