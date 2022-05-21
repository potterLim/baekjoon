using System;

namespace _08_A_B
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            double result = double.Parse(numsStr[0]) / double.Parse(numsStr[1]);
            Console.WriteLine(result);
        }
    }
}
