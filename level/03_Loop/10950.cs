using System;

namespace _02_A_B_3
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());

            string twoNumStr;
            string[] numsStr = new string[2];
            int[] nums = new int[2];
            int sum;

            for (int i = 0; i < n; ++i)
            {
                twoNumStr = Console.ReadLine();
                numsStr = twoNumStr.Split(" ");
                nums[0] = int.Parse(numsStr[0]);
                nums[1] = int.Parse(numsStr[1]);
                sum = nums[0] + nums[1];
                Console.WriteLine(sum);
            }
        }
    }
}
