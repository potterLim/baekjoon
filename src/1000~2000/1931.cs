using System;
using System.Collections.Generic;

public class Program
{
    static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());
        List<(int Start, int End)> meetings = new List<(int, int)>();

        for (int i = 0; i < n; i++)
        {
            string[] input = Console.ReadLine().Split(' ');
            int start = int.Parse(input[0]);
            int end = int.Parse(input[1]);
            meetings.Add((start, end));
        }

        meetings.Sort(CompareMeetings);

        int count = 0;
        int lastEndTime = 0;

        for (int i = 0; i < meetings.Count; i++)
        {
            if (meetings[i].Start >= lastEndTime)
            {
                count++;
                lastEndTime = meetings[i].End;
            }
        }

        Console.WriteLine(count);
    }

    private static int CompareMeetings((int Start, int End) a, (int Start, int End) b)
    {
        if (a.End == b.End)
        {
            return a.Start.CompareTo(b.Start);
        }
        return a.End.CompareTo(b.End);
    }
}