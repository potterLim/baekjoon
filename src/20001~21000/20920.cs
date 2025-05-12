using System;
using System.Collections.Generic;
using System.Text;

namespace VocabularyBuilder
{
    public class Program
    {
        public static void Main()
        {
            string[] inputs = Console.ReadLine().Split();
            int wordCount = int.Parse(inputs[0]);
            int minLength = int.Parse(inputs[1]);

            Dictionary<string, int> wordFrequency = new Dictionary<string, int>();

            for (int i = 0; i < wordCount; ++i)
            {
                string word = Console.ReadLine();

                if (word.Length < minLength)
                {
                    continue;
                }

                if (wordFrequency.ContainsKey(word))
                {
                    ++wordFrequency[word];
                }
                else
                {
                    wordFrequency[word] = 1;
                }
            }

            List<string> vocabulary = new List<string>(wordFrequency.Keys);

            vocabulary.Sort((a, b) =>
            {
                if (wordFrequency[a] != wordFrequency[b])
                {
                    return wordFrequency[b] - wordFrequency[a];
                }

                if (a.Length != b.Length)
                {
                    return b.Length - a.Length;
                }

                return string.Compare(a, b, StringComparison.Ordinal);
            });

            StringBuilder sb = new StringBuilder();

            foreach (string word in vocabulary)
            {
                sb.AppendLine(word);
            }

            Console.Write(sb.ToString());
        }
    }
}