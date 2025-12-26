using System;
using System.Numerics;

namespace boj
{
    public static class Program
    {
        private const int MOD = 1000000000;

        public static void Main(string[] args)
        {
            string line = Console.ReadLine();
            if (string.IsNullOrWhiteSpace(line))
            {
                return;
            }

            string[] parts = line.Split(' ', StringSplitOptions.RemoveEmptyEntries);
            if (parts.Length != 2)
            {
                return;
            }

            int n;
            int k;

            if (!int.TryParse(parts[0], out n) || !int.TryParse(parts[1], out k))
            {
                return;
            }

            int result = solve(n, k);
            Console.WriteLine(result);
        }

        private static int solve(int n, int k)
        {
            if (k < 1)
            {
                return -1;
            }

            if (n < 3)
            {
                return -1;
            }

            if (k > n - 2)
            {
                return -1;
            }

            if (k == 1)
            {
                return 1;
            }

            int r = k - 1;

            BigInteger a = binom(n + k - 2, r);
            BigInteger b = binom(n - 3, r);

            BigInteger value = a * b;

            value /= k;

            int answer = (int)(value % MOD);
            if (answer < 0)
            {
                answer += MOD;
            }

            return answer;
        }

        private static BigInteger binom(int n, int r)
        {
            if (r < 0 || r > n)
            {
                return BigInteger.Zero;
            }

            int rr = r;
            if (rr > n - rr)
            {
                rr = n - rr;
            }

            BigInteger result = BigInteger.One;

            for (int i = 1; i <= rr; ++i)
            {
                result *= (n - rr + i);
                result /= i;
            }

            return result;
        }
    }
}
