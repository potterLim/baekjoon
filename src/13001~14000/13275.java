import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.next();
        int n = s.length();
        
        int tLen = (n << 1) + 3;
        char[] t = new char[tLen];

        int ti = 0;
        t[ti] = '^';
        ++ti;
        t[ti] = '#';
        ++ti;

        int i = 0;
        while (i < n) {
            t[ti] = s.charAt(i);
            ++ti;
            t[ti] = '#';
            ++ti;
            ++i;
        }

        t[ti] = '$';

        int[] p = new int[tLen];

        int center = 0;
        int right = 0;
        int best = 0;

        int pos = 1;
        while (pos < tLen - 1) {
            int mirror = (center << 1) - pos;

            if (pos < right) {
                int bound = right - pos;
                int mirrored = p[mirror];

                if (mirrored < bound) {
                    p[pos] = mirrored;
                } else {
                    p[pos] = bound;
                }
            } else {
                p[pos] = 0;
            }

            while (t[pos + p[pos] + 1] == t[pos - p[pos] - 1]) {
                ++p[pos];
            }

            int reach = pos + p[pos];
            if (reach > right) {
                center = pos;
                right = reach;
            }

            if (p[pos] > best) {
                best = p[pos];
            }

            ++pos;
        }

        System.out.print(best);
    }
}