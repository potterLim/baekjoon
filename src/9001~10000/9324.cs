using System;

public class Program
{
    public static void Main(string[] args)
    {
        int testCaseCount = int.Parse(Console.ReadLine());

        for (int testCase = 1; testCase <= testCaseCount; testCase++)
        {
            string message = Console.ReadLine();
            string result = isValidMessage(message) ? "OK" : "FAKE";
            Console.WriteLine(result);
        }
    }

    private static bool isValidMessage(string message)
    {
        int[] charCount = new int[26];

        for (int i = 0; i < message.Length; i++)
        {
            int index = message[i] - 'A';
            charCount[index]++;

            if (charCount[index] == 3)
            {
                if (i == message.Length - 1 || message[i + 1] != message[i])
                {
                    return false;
                }

                charCount[index] = 0;
                i++;
            }
        }

        return true;
    }
}