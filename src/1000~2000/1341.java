import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger a = new BigInteger(st.nextToken());
        BigInteger b = new BigInteger(st.nextToken());

        if (a.signum() == 0) {
            System.out.println("-");
            return;
        }

        if (b.equals(BigInteger.ONE)) {
            System.out.println("*");
            return;
        }

        if (b.testBit(0) == false) {
            System.out.println("-1");
            return;
        }

        BigInteger ONE = BigInteger.ONE;
        BigInteger TWO = BigInteger.TWO;

        for (int L = 1; L <= 60; ++L) {
            BigInteger pow2 = TWO.pow(L);
            if (pow2.mod(b).equals(ONE)) {
                BigInteger numerator = pow2.subtract(ONE).multiply(a);
                BigInteger[] qr = numerator.divideAndRemainder(b);
                if (qr[1].signum() != 0) {
                    continue;
                }
                BigInteger T = qr[0];
                StringBuilder sb = new StringBuilder();
                for (int i = L - 1; i >= 0; --i) {
                    sb.append(T.testBit(i) ? '*' : '-');
                }
                System.out.println(sb.toString());
                return;
            }
        }

        System.out.println("-1");
    }
}
