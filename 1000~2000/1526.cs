using System;

namespace ConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            int number = int.Parse(Console.ReadLine());

            for (int i = number; i >= 4; i--)
            {
                if (ContainsOnlyFourAndSeven(i))
                {
                    Console.WriteLine(i);
                    break;
                }
            }
        }

        static bool ContainsOnlyFourAndSeven(int number)
        {
            while (number > 0)
            {
                int digit = number % 10;
                if (digit != 4 && digit != 7)
                {
                    return false;
                }
                number /= 10;
            }
            return true;
        }
    }
}