using System;
using System.Collections.Generic;

namespace boj
{
    public static class Program
    {
        public static void Main(string[] args)
        {
            int treeCount = int.Parse(Console.ReadLine());

            string[] initialHeightsTokens = Console.ReadLine().Split(' ');
            string[] growthRatesTokens = Console.ReadLine().Split(' ');

            long total = 0L;

            for (int i = 0; i < treeCount; ++i)
            {
                total += long.Parse(initialHeightsTokens[i]);
            }

            int[] growthRates = new int[treeCount];
            for (int i = 0; i < treeCount; ++i)
            {
                growthRates[i] = int.Parse(growthRatesTokens[i]);
            }

            Array.Sort(growthRates);

            for (int dayIndex = 0; dayIndex < treeCount; ++dayIndex)
            {
                total += (long)growthRates[dayIndex] * (long)dayIndex;
            }

            Console.WriteLine(total);
        }
    }
}
