using System;

public class Program
{
    static void Main(string[] args)
    {
        string[] sizeInput = Console.ReadLine().Split();
        int n = int.Parse(sizeInput[0]);
        int m = int.Parse(sizeInput[1]);

        int[,] array = new int[n + 1, m + 1];
        int[,] prefixSum = new int[n + 1, m + 1];

        for (int i = 1; i <= n; i++)
        {
            string[] rowInput = Console.ReadLine().Split();
            for (int j = 1; j <= m; j++)
            {
                array[i, j] = int.Parse(rowInput[j - 1]);
                prefixSum[i, j] = array[i, j] + prefixSum[i - 1, j] + prefixSum[i, j - 1] - prefixSum[i - 1, j - 1];
            }
        }

        int k = int.Parse(Console.ReadLine());

        for (int q = 0; q < k; q++)
        {
            string[] query = Console.ReadLine().Split();
            int i = int.Parse(query[0]);
            int j = int.Parse(query[1]);
            int x = int.Parse(query[2]);
            int y = int.Parse(query[3]);

            int sum = prefixSum[x, y] - prefixSum[i - 1, y] - prefixSum[x, j - 1] + prefixSum[i - 1, j - 1];

            Console.WriteLine(sum);
        }
    }
}