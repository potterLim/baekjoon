using System;
using System.Text.RegularExpressions;

public class Program
{
    public static void Main(string[] args)
    {
        string inputString = string.Empty;

        while (true)
        {
            string input = Console.ReadLine();
            inputString += input;

            if (input.Length >= 5 && input.Substring(input.Length - 5, 5) == "E-N-D")
            {
                break;
            }
        }

        inputString = inputString.Substring(0, inputString.Length - 6);

        const string Pattern = "[a-zA-Z]+(?:-[a-zA-Z]+)*";
        MatchCollection matches = Regex.Matches(inputString, Pattern);

        string[] words = new string[matches.Count];
        for (int i = 0; i < matches.Count; i++)
        {
            words[i] = matches[i].Value;
        }

        int maxLength = 0;
        int maxIndex = 0;

        for (int i = 0; i < words.Length; ++i)
        {
            if (words[i].Length > maxLength)
            {
                maxLength = words[i].Length;
                maxIndex = i;
            }
        }

        Console.WriteLine(words[maxIndex].ToLower());
    }
}