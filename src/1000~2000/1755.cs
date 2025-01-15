using System;
using System.Collections.Generic;

public class Program
{
    static void Main(string[] args)
    {
        Dictionary<int, string> digitToWordMap = new Dictionary<int, string>
        {
            { 0, "zero" },
            { 1, "one" },
            { 2, "two" },
            { 3, "three" },
            { 4, "four" },
            { 5, "five" },
            { 6, "six" },
            { 7, "seven" },
            { 8, "eight" },
            { 9, "nine" }
        };

        Dictionary<string, int> wordToDigitMap = new Dictionary<string, int>();
        foreach (var kvp in digitToWordMap)
        {
            wordToDigitMap[kvp.Value] = kvp.Key;
        }

        string input = Console.ReadLine();
        string[] rangeInput = input.Split(' ');
        int startNumber = int.Parse(rangeInput[0]);
        int endNumber = int.Parse(rangeInput[1]);
        int totalNumbers = endNumber - startNumber + 1;

        string[] numberWords = new string[totalNumbers];

        for (int i = 0; i < totalNumbers; ++i)
        {
            int currentNumber = i + startNumber;
            string currentNumberString = currentNumber.ToString();
            numberWords[i] = "";

            for (int j = 0; j < currentNumberString.Length; ++j)
            {
                if (j != 0)
                {
                    numberWords[i] += " ";
                }
                numberWords[i] += digitToWordMap[int.Parse(currentNumberString[j].ToString())];
            }
        }

        Array.Sort(numberWords);

        string[] sortedNumbers = new string[totalNumbers];

        for (int i = 0; i < totalNumbers; ++i)
        {
            sortedNumbers[i] = "";
            string[] wordSegments = numberWords[i].Split(' ');

            for (int j = 0; j < wordSegments.Length; ++j)
            {
                sortedNumbers[i] += wordToDigitMap[wordSegments[j]];
            }
        }

        for (int i = 0; i < sortedNumbers.Length; ++i)
        {
            Console.Write(sortedNumbers[i]);

            if (i % 10 == 9)
            {
                Console.WriteLine();
            }
            else
            {
                Console.Write(" ");
            }
        }
    }
}