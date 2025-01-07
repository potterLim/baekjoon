using System;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        string[] input = Console.ReadLine().Split(' ');
        int numberOfSetStrings = int.Parse(input[0]);
        int numberOfQueryStrings = int.Parse(input[1]);

        HashSet<string> stringSet = new HashSet<string>();
        for (int i = 0; i < numberOfSetStrings; i++)
        {
            stringSet.Add(Console.ReadLine());
        }

        int matchCount = 0;
        for (int i = 0; i < numberOfQueryStrings; i++)
        {
            if (stringSet.Contains(Console.ReadLine()))
            {
                matchCount++;
            }
        }

        Console.WriteLine(matchCount);
    }
}
