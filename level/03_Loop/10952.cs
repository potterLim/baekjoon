using System;

namespace _12_A_B_5
{
    class Program
    {
        static void Main(string[] args)
        {
            string numStr;

            while (true)
            {
                numStr = Console.ReadLine();

                int num1 = int.Parse(numStr.Split(" ")[0]);
                int num2 = int.Parse(numStr.Split(" ")[1]);

                if (num1 == 0 && num2 == 0)
                {
                    break;
                }

                int sum = num1 + num2;
                Console.WriteLine(sum);
            }
        }
    }
}
