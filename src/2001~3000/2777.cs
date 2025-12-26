using System;

namespace boj
{
    public static class Program
    {
        public static void Main(string[] args)
        {
            string line = Console.ReadLine();
            int testCaseCount;

            if (!int.TryParse(line, out testCaseCount))
            {
                return;
            }

            for (int i = 0; i < testCaseCount; ++i)
            {
                string input = Console.ReadLine();
                long n;

                if (!long.TryParse(input, out n))
                {
                    Console.WriteLine("-1");
                    continue;
                }

                int result = getMinDigitCountOrMinusOne(n);
                Console.WriteLine(result);
            }
        }

        private static int getMinDigitCountOrMinusOne(long n)
        {
            if (n == 1)
            {
                return 1;
            }

            int digitCount = 0;

            for (int d = 9; d >= 2; --d)
            {
                while (n % d == 0)
                {
                    n /= d;
                    digitCount++;
                }
            }

            if (n != 1)
            {
                return -1;
            }

            return digitCount;
        }
    }
}
