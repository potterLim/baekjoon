using System;
using System.Text;

namespace boj
{
    public static class Program
    {
        public static void Main(string[] args)
        {
            string firstLine = Console.ReadLine();
            int testCaseCount;

            if (!int.TryParse(firstLine, out testCaseCount))
            {
                return;
            }

            int[] ns = new int[testCaseCount];
            int maxN = 0;

            for (int i = 0; i < testCaseCount; ++i)
            {
                string line = Console.ReadLine();
                int n;

                if (!int.TryParse(line, out n))
                {
                    n = 0;
                }

                ns[i] = n;

                if (n > maxN)
                {
                    maxN = n;
                }
            }

            long[] prefixPhiSum = buildPrefixPhiSum(maxN);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < testCaseCount; ++i)
            {
                int n = ns[i];
                long answer = 1 + 2 * prefixPhiSum[n];

                sb.Append(answer);

                if (i + 1 < testCaseCount)
                {
                    sb.Append('\n');
                }
            }

            Console.Write(sb.ToString());
        }

        private static long[] buildPrefixPhiSum(int maxN)
        {
            int[] phi = computeTotients(maxN);
            long[] prefix = new long[maxN + 1];

            prefix[0] = 0;

            for (int i = 1; i <= maxN; ++i)
            {
                prefix[i] = prefix[i - 1] + phi[i];
            }

            return prefix;
        }

        private static int[] computeTotients(int maxN)
        {
            int[] phi = new int[maxN + 1];

            for (int i = 0; i <= maxN; ++i)
            {
                phi[i] = i;
            }

            for (int p = 2; p <= maxN; ++p)
            {
                if (phi[p] != p)
                {
                    continue;
                }

                for (int k = p; k <= maxN; k += p)
                {
                    phi[k] -= phi[k] / p;
                }
            }

            if (maxN >= 1)
            {
                phi[1] = 1;
            }

            return phi;
        }
    }
}
