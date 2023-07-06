using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> wordList = new List<string>();
            while (true)
            {
                string word = Console.ReadLine().Trim();
                if (word == "-")
                {
                    break;
                }
                wordList.Add(word);
            }

            List<string> checkList = new List<string>();
            while (true)
            {
                string checkWord = Console.ReadLine().Trim();
                if (checkWord == "#")
                {
                    break;
                }
                checkList.Add(checkWord);
            }

            foreach (string checkWord in checkList)
            {
                Dictionary<char, int> charCountDict = new Dictionary<char, int>();
                foreach (char c in checkWord)
                {
                    charCountDict[c] = 0;
                    foreach (string word in wordList)
                    {
                        if (word.Contains(c))
                        {
                            bool isSubset = true;
                            foreach (char letter in word)
                            {
                                if (word.Count(x => x == letter) > checkWord.Count(x => x == letter))
                                {
                                    isSubset = false;
                                    break;
                                }
                            }
                            if (isSubset == true)
                            {
                                charCountDict[c]++;
                            }
                        }
                    }
                }

                List<char> minCharList = new List<char>();
                foreach (var item in charCountDict)
                {
                    if (item.Value == charCountDict.Values.Min())
                    {
                        minCharList.Add(item.Key);
                    }
                }
                int minCount = charCountDict.Values.Min();

                List<char> maxCharList = new List<char>();
                foreach (var item in charCountDict)
                {
                    if (item.Value == charCountDict.Values.Max())
                    {
                        maxCharList.Add(item.Key);
                    }
                }
                int maxCount = charCountDict.Values.Max();

                minCharList.Sort();
                maxCharList.Sort();

                Console.WriteLine($"{string.Join("", minCharList)} {minCount} {string.Join("", maxCharList)} {maxCount}");
            }
        }
    }
}