using System;

namespace _13_A_B_4
{
    class Program
    {
        static void Main(string[] args)
        {
            string numStr;

            while (true)
            {
                numStr = Console.ReadLine();

                if(numStr == null)
                {
                    break;
                }

                int num1 = int.Parse(numStr.Split(" ")[0]);
                int num2 = int.Parse(numStr.Split(" ")[1]);

                int sum = num1 + num2;
                Console.WriteLine(sum);
            }
        }
    }
}
