using System;

namespace _01_CompareTwoNumber
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int num1 = int.Parse(numsStr[0]);
            int num2 = int.Parse(numsStr[1]);

            if (num1 > num2)
            {
                Console.WriteLine(">");
            }

            else if (num1 < num2)
            {
                Console.WriteLine("<");
            }

            else
            {
                Console.WriteLine("==");
            }
        }
    }
}
