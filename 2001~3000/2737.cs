using System;

namespace ConsoleApp1
{
    public class Program
    {
        static void Main(string[] args)
        {
            int testCases = int.Parse(Console.ReadLine());

            for (int testCase = 0; testCase < testCases; testCase++)
            {
                int targetNumber = int.Parse(Console.ReadLine());
                int count = 0;
                int divisor = 1;
                targetNumber--;

                while (targetNumber > 0)
                {
                    divisor++;
                    targetNumber -= divisor;
                    if (targetNumber % divisor == 0)
                    {
                        count++;
                    }
                }

                    Console.WriteLine(count);
            }
        }
    }
}