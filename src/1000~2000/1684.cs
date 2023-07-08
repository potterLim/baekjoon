using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int arrayLength = int.Parse(Console.ReadLine());
            int[] numbers = Console.ReadLine().Split().Select(int.Parse).ToArray();

            for (int i = 0; i < arrayLength - 1; i++)
            {
                for (int j = 0; j < arrayLength - i - 1; j++)
                {
                    if (numbers[j] > numbers[j + 1])
                    {
                        int temp = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = temp;
                    }
                }
            }

            List<int> differences = new List<int>();
            for (int i = 1; i < arrayLength; i++)
            {
                differences.Add(numbers[i] - numbers[i - 1]);
            }

            int gcd = differences[0];
            for (int i = 1; i < differences.Count; i++)
            {
                int previousGcd = gcd;
                int currentDifference = differences[i];
                while (currentDifference != 0)
                {
                    int temp = previousGcd % currentDifference;
                    previousGcd = currentDifference;
                    currentDifference = temp;
                }
                gcd = previousGcd;
            }
            Console.WriteLine(gcd);
        }
    }
}
