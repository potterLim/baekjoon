using System;
using System.Collections.Generic;
using System.Linq;

public static class Program
{
    public static void Main(string[] args)
    {
        int count = int.Parse(Console.ReadLine());
        int[] original = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();

        var sortedDistinct = original.Distinct().OrderBy(x => x).ToArray();
        var rankDictionary = new Dictionary<int, int>();

        for (int i = 0; i < sortedDistinct.Length; i++)
        {
            rankDictionary[sortedDistinct[i]] = i;
        }

        int[] compressed = original.Select(x => rankDictionary[x]).ToArray();
        Console.WriteLine(string.Join(" ", compressed));
    }
}