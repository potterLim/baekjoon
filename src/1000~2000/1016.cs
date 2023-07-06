using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string tmp = Console.ReadLine();
            long min = long.Parse(tmp.Split()[0]);
            long max = long.Parse(tmp.Split()[1]);

            long ans = max - min + 1;

            bool[] sieve = new bool[max - min + 1];
            long i = 2;

            while (i * i <= max)
            {
                long sNum = min / (i * i);
                if (min % (i * i) != 0)
                {
                    sNum += 1;
                }

                while (sNum * (i * i) <= max)
                {
                    if (sieve[sNum * (i * i) - min] == false)
                    {
                        sieve[sNum * (i * i) - min] = true;
                        ans--;
                    }
                    sNum++;
                }
                i++;
            }

            Console.WriteLine(ans);
        }
    }
}
