using System;

namespace _07_AxB
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int product = int.Parse(numsStr[0]) * int.Parse(numsStr[1]);
            Console.WriteLine(product);
        }
    }
}
