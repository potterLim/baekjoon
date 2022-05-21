using System;

namespace _02_Max
{
    class Program
    {
        static void Main(string[] args)
        {
            int maxValue = -1;
            int maxIdx = -1;
            int[] nums = new int[9];

            for (int i = 0; i < 9; i++)
            {
                nums[i] = int.Parse(Console.ReadLine());

                if (nums[i] > maxValue)
                {
                    maxValue = nums[i];
                    maxIdx = i + 1;
                }
            }

            Console.WriteLine(maxValue);
            Console.WriteLine(maxIdx);
        }
    }
}
