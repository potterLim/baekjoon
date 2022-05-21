using System;

namespace _07_OverAverage
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());

            for (int i = 0; i < n; i++)
            {
                string nums = Console.ReadLine();
                int count = int.Parse(nums.Split(" ")[0]);
                int sum = 0;
                float average = 0;
                int check = 0;

                for (int j = 1; j <= count; j++)
                {
                    sum += int.Parse(nums.Split(" ")[j]);
                }

                average = sum / (float)count;

                for (int j = 1; j <= count; j++)
                {
                    if (int.Parse(nums.Split(" ")[j]) > average)
                    {
                        check++;
                    }
                }

                Console.WriteLine("{0:f3}%", check * 100 / (double)count);
            }
        }
    }
}
