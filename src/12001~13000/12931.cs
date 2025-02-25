using System;
using System.Linq;

namespace ConsoleApp1
{
    public class Program
    {
        public static void Main(string[] args)
        {
            int arraySize = int.Parse(Console.ReadLine());
            int[] targetArray = Console.ReadLine().Split().Select(int.Parse).ToArray();
            int incrementOperations = 0;
            int maxDoubleOperations = 0;

            foreach (int number in targetArray)
            {
                int value = number;
                int bitCount = 0;
                while (value > 0)
                {
                    if ((value & 1) == 1)
                    {
                        incrementOperations++;
                    }
                    bitCount++;
                    value /= 2;
                }
                maxDoubleOperations = Math.Max(maxDoubleOperations, bitCount > 0 ? bitCount - 1 : 0);
            }

            Console.WriteLine(incrementOperations + maxDoubleOperations);
        }
    }
}