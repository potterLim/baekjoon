using System;

namespace _01_MinAndMax
{
    class Program
    {
        static void Main(string[] args)
        {
            int count = int.Parse(Console.ReadLine());
            string numStr = Console.ReadLine();
            string[] numsStrs = numStr.Split(" ");

            int max = -1000000;
            int min = 1000000;
            for (int i = 0; i < count; i++)
            {
                if (int.Parse(numsStrs[i]) > max)
                {
                    max = int.Parse(numsStrs[i]);
                }

                if (int.Parse(numsStrs[i]) < min)
                {
                    min = int.Parse(numsStrs[i]);
                }
            }

            Console.WriteLine($"{min} {max}");
        }
    }
}
