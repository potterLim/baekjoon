using System;

namespace _14_AdditionCycle
{
    class Program
    {
        static void Main(string[] args)
        {
            int num = int.Parse(Console.ReadLine());
            int count = 0;
            int cycleNum = num;

            do
            {
                cycleNum = cycleNum % 10 * 10 + (cycleNum / 10 + cycleNum % 10) % 10;
                count++;
            } while (num != cycleNum);

            Console.WriteLine(count);
        }
    }
}
