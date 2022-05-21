using System;

namespace _03_Sum
{
    class Program
    {
        static void Main(string[] args)
        {
            int sum = 0;
            int n = int.Parse(Console.ReadLine());

            for (int i = 1; i <= n; ++i)
            {
                sum += i;
            }

            Console.WriteLine(sum);
        }
    }
}
