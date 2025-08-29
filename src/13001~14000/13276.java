import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

final class Main {
    private static boolean matchAt(String text, String pattern, int startIndex) {
        int patternLength = pattern.length();
        if (startIndex + patternLength > text.length()) {
            return false;
        }
        for (int k = 0; k < patternLength; ++k) {
            if (text.charAt(startIndex + k) != pattern.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine().trim();
        String startPattern = br.readLine().trim();
        String endPattern = br.readLine().trim();

        int textLength = inputString.length();
        boolean[] canStart = new boolean[textLength];
        boolean[] canEnd = new boolean[textLength];

        for (int i = 0; i < textLength; ++i) {
            if (matchAt(inputString, startPattern, i)) {
                canStart[i] = true;
            }
        }
        for (int j = 0; j < textLength; ++j) {
            int checkIndex = j - endPattern.length() + 1;
            if (checkIndex >= 0 && matchAt(inputString, endPattern, checkIndex)) {
                canEnd[j] = true;
            }
        }

        int modPrime1 = 1_000_000_007;
        int modPrime2 = 1_000_000_009;
        int base1 = 911382323;
        int base2 = 972663749;

        int[] power1 = new int[textLength + 1];
        int[] power2 = new int[textLength + 1];
        int[] prefixHash1 = new int[textLength + 1];
        int[] prefixHash2 = new int[textLength + 1];

        power1[0] = 1;
        power2[0] = 1;
        for (int i = 0; i < textLength; ++i) {
            power1[i + 1] = (int) ((long) power1[i] * base1 % modPrime1);
            power2[i + 1] = (int) ((long) power2[i] * base2 % modPrime2);
            int charValue = inputString.charAt(i);
            prefixHash1[i + 1] = (int) (((long) prefixHash1[i] * base1 + charValue) % modPrime1);
            prefixHash2[i + 1] = (int) (((long) prefixHash2[i] * base2 + charValue) % modPrime2);
        }

        HashSet<Long> uniqueHashes = new HashSet<>();
        int startLen = startPattern.length();
        int endLen = endPattern.length();

        for (int i = 0; i < textLength; ++i) {
            if (!canStart[i]) {
                continue;
            }
            int minEndIndex = Math.max(i + startLen - 1, i);
            for (int j = minEndIndex; j < textLength; ++j) {
                if (!canEnd[j]) {
                    continue;
                }
                if (j - endLen + 1 < i) {
                    continue;
                }
                int substringLength = j - i + 1;
                int hash1 = prefixHash1[i + substringLength] - (int) ((long) prefixHash1[i] * power1[substringLength] % modPrime1);
                if (hash1 < 0) {
                    hash1 += modPrime1;
                }

                int hash2 = prefixHash2[i + substringLength] - (int) ((long) prefixHash2[i] * power2[substringLength] % modPrime2);
                if (hash2 < 0) {
                    hash2 += modPrime2;
                }

                long combinedHash = ((hash1 & 0xffffffffL) << 32) | (hash2 & 0xffffffffL);
                uniqueHashes.add(combinedHash);
            }
        }

        System.out.println(uniqueHashes.size());
    }
}
