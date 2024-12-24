using System;
using System.Collections.Generic;

public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        HashSet<string> substrings = new HashSet<string>();

        for (int i = 0; i < input.Length; i++)
        {
            for (int j = i + 1; j <= input.Length; j++)
            {
                substrings.Add(input.Substring(i, j - i));
            }
        }

        Console.WriteLine(substrings.Count);

    }
}