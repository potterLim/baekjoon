using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main()
        {
            int countDigits = int.Parse(Console.ReadLine());
            long[] fibonacci = new long[countDigits + 1];

            fibonacci[1] = 1;
            if (countDigits >= 2)
            {
                fibonacci[2] = 1;
            }

            for (int i = 3; i <= countDigits; i++)
            {
                fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            }

            Console.WriteLine(fibonacci[countDigits]);
        }
    }
}