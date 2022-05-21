using System;
using System.Text;

namespace _04_FastA_B
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

            for (int i = 0; i < n; ++i)
            {
                twoNumStr = Console.ReadLine();
                numsStr = twoNumStr.Split(" ");
                nums[0] = int.Parse(numsStr[0]);
                nums[1] = int.Parse(numsStr[1]);
                sum = nums[0] + nums[1];
                sb.Append(sum);
                sb.Append("\n");
            }

            Console.WriteLine(sb);
        }
    }
}
