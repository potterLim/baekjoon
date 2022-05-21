using System;
using System.Text;

namespace _07_A_B_7
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
            StringBuilder sb = new StringBuilder(128);

            for (int i = 1; i <= n; ++i)
            {
                twoNumStr = Console.ReadLine();
                numsStr = twoNumStr.Split(" ");
                nums[0] = int.Parse(numsStr[0]);
                nums[1] = int.Parse(numsStr[1]);
                sum = nums[0] + nums[1];
                sb.Append($"Case #{i}: {sum}\n");
            }

            Console.WriteLine(sb);
        }
    }
}
