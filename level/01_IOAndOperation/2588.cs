using System;

namespace _13_Multiplication
{
    class Program
    {
        static void Main(string[] args)
        {
            int num1 = int.Parse(Console.ReadLine());
            string num2Str = Console.ReadLine();
            int num2 = int.Parse(num2Str);
            int charNumToInt;

            for (int i = num2Str.Length - 1; i >= 0; --i)
            {
                charNumToInt = num2Str[i] - 48;
                Console.WriteLine(num1 * charNumToInt);
            }
            Console.WriteLine(num1 * num2);
        }
    }
}
