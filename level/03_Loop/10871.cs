using System;

namespace _11_NumberLessThanInput
{
    class Program
    {
        static void Main(string[] args)
        {
            string inputStr = Console.ReadLine();
            int ComparisonValue = int.Parse(inputStr.Split(" ")[1]);
            int count = int.Parse(inputStr.Split(" ")[0]);

            string numsStr = Console.ReadLine();
            string[] numsStrs = numsStr.Split(" ");

            for (int i = 0; i < count; i++)
            {
                if (ComparisonValue > int.Parse(numsStrs[i]))
                {
                    Console.Write($"{int.Parse(numsStrs[i])} ");
                }
            }
        }
    }
}
