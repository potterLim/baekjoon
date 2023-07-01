using System;

namespace ConsoleApp1
{
    public class Program
    {
        static void Main(string[] args)
        {
            string S = Console.ReadLine();
            string P = Console.ReadLine();

            int count = 0;

            for (int i = 0; i < P.Length;)
            {
                int max = 0;
                for (int j = 0; j < S.Length; j++)
                {
                    int tmp = 0;

                    while (j + tmp < S.Length && i + tmp < P.Length && S[j + tmp] == P[i + tmp])
                    {
                        tmp++;
                    }

                    if (max < tmp)
                    {
                        max = tmp;
                    }
                }

                i += max;
                count++;
            }

            Console.WriteLine(count);
        }
    }
}