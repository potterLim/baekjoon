using System;

namespace _03_CountNum
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] countNums = new int[10];

            int num1 = int.Parse(Console.ReadLine());
            int num2 = int.Parse(Console.ReadLine());
            int num3 = int.Parse(Console.ReadLine());

            int product = num1 * num2 * num3;

            string productStr = product.ToString();

            for (int i = 0; i < productStr.Length; i++)
            {
                countNums[(int)productStr[i] - 48]++;
            }

            for (int i = 0; i < 10; i++)
            {
                Console.WriteLine(countNums[i]);
            }
        }
    }
}
