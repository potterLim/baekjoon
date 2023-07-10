using System;

namespace ConsoleApp1
{
    class Program
    {
        public static int CalculateMinimumCost(int capacity, int itemCount, int[][] items)
        {
            int[] minimumCosts = new int[capacity + 101];
            Array.Fill(minimumCosts, int.MaxValue);
            minimumCosts[0] = 0;

            for (int i = 0; i < itemCount; i++)
            {
                int itemCost = items[i][0];
                int itemReward = items[i][1];

                for (int j = itemReward; j < capacity + 101; j++)
                {
                    int previousCost = minimumCosts[j - itemReward];
                    if (previousCost != int.MaxValue)
                    {
                        minimumCosts[j] = Math.Min(minimumCosts[j], itemCost + previousCost);
                    }
                }
            }

            int result = int.MaxValue;
            for (int i = capacity; i < capacity + 101; i++)
            {
                result = Math.Min(result, minimumCosts[i]);
            }

            return result;
        }

        public static void Main(string[] args)
        {
            string[] input = Console.ReadLine().Split(' ');
            int capacity = int.Parse(input[0]);
            int itemCount = int.Parse(input[1]);

            int[][] items = new int[itemCount][];
            for (int i = 0; i < itemCount; i++)
            {
                input = Console.ReadLine().Split(' ');
                int itemCost = int.Parse(input[0]);
                int itemReward = int.Parse(input[1]);
                items[i] = new int[] { itemCost, itemReward };
            }

            int result = CalculateMinimumCost(capacity, itemCount, items);
            Console.WriteLine(result);
        }
    }
}
