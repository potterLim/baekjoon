using System;

public class Program
{
    public static void Main(string[] args)
    {
        string firstString = Console.ReadLine();
        string secondString = Console.ReadLine();

        int firstLength = firstString.Length;
        int secondLength = secondString.Length;

        int[,] dpTable = new int[firstLength + 1, secondLength + 1];

        for (int i = 1; i <= firstLength; i++)
        {
            for (int j = 1; j <= secondLength; j++)
            {
                if (firstString[i - 1] == secondString[j - 1])
                {
                    dpTable[i, j] = dpTable[i - 1, j - 1] + 1;
                }
                else
                {
                    dpTable[i, j] = Math.Max(dpTable[i - 1, j], dpTable[i, j - 1]);
                }
            }
        }

        Console.WriteLine(dpTable[firstLength, secondLength]);
    }
}