using System;

namespace CountRemainder
{
    class Program
    {
        static void Main(string[] args)
        {
            bool[] remainder = new bool[42];
            int count = 0;

            for (int i = 0; i < 10; i++)
            {
                remainder[int.Parse(Console.ReadLine()) % 42] = true;
            }

            for (int i = 0; i < 42; i++)
            {
                if (remainder[i] == true)
                {
                    count++;
                }
            }

            Console.WriteLine(count);
        }
    }
}
