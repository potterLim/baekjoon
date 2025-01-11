using System;

public class Program
{
    public static void Main(string[] args)
    {
        const int ASCII_OFFSET = 65;
        const int ALPHABET_COUNT = 26;

        int testCaseCount = int.Parse(Console.ReadLine());

        for (int i = 0; i < testCaseCount; ++i)
        {
            string inputString = Console.ReadLine();
            bool[] existAlphabet = new bool[ALPHABET_COUNT];
            int sum = 0;

            foreach (char c in inputString)
            {
                existAlphabet[c - ASCII_OFFSET] = true;
            }

            for (int j = 0; j < ALPHABET_COUNT; ++j)
            {
                if (existAlphabet[j] != true)
                {
                    sum += j + ASCII_OFFSET;
                }
            }

            Console.WriteLine(sum);
        }
    }
}