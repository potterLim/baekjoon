using System;

namespace _09_Operation
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int sum = int.Parse(numsStr[0]) + int.Parse(numsStr[1]);
            int sub = int.Parse(numsStr[0]) - int.Parse(numsStr[1]);
            int product = int.Parse(numsStr[0]) * int.Parse(numsStr[1]);
            int quotient = int.Parse(numsStr[0]) / int.Parse(numsStr[1]);
            int remainder = int.Parse(numsStr[0]) % int.Parse(numsStr[1]);

            Console.WriteLine(sum);
            Console.WriteLine(sub);
            Console.WriteLine(product);
            Console.WriteLine(quotient);
            Console.WriteLine(remainder);
        }
    }
}
