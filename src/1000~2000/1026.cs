using System;
using System.Linq;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int size = int.Parse(Console.ReadLine());

            string[] input = Console.ReadLine().Split();
            int[] arrayA = new int[size];

            for (int i = 0; i <size; i++)
            {
                arrayA[i] = int.Parse(input[i]);
            }
            Array.Sort(arrayA);

            input = Console.ReadLine().Split();
            int[] arrayB = new int[size];

            for (int i = 0; i < size; i++)
            {
                arrayB[i] = int.Parse(input[i]);
            }
            Array.Sort(arrayB);
            Array.Reverse(arrayB);

            int result = 0;
            for (int i = 0; i < size; i++)
            {
                result += arrayA[i] * arrayB[i];
            }

            Console.WriteLine(result);
        }
    }
}