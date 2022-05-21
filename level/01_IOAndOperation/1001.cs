using System;

namespace _06_A_B
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int sub = int.Parse(numsStr[0]) - int.Parse(numsStr[1]);
            Console.WriteLine(sub);
        }
    }
}
