using System;

public class Program
{
    static void Main(string[] args)
    {
        string word = Console.ReadLine();
        int length = word.Length;
        string smallestWord = null;

        for (int i = 1; i < length - 1; i++)
        {
            for (int j = i + 1; j < length; j++)
            {
                string part1 = reverseSubstring(word, 0, i);
                string part2 = reverseSubstring(word, i, j);
                string part3 = reverseSubstring(word, j, length);

                string combined = part1 + part2 + part3;

                if (smallestWord == null || string.Compare(combined, smallestWord, StringComparison.Ordinal) < 0)
                {
                    smallestWord = combined;
                }
            }
        }

        Console.WriteLine(smallestWord);
    }

    private static string reverseSubstring(string word, int start, int end)
    {
        char[] chars = word.Substring(start, end - start).ToCharArray();
        Array.Reverse(chars);
        string reversedSubstring = new string(chars);
        return reversedSubstring;
    }
}